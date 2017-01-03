#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <time.h>
#include <unistd.h>

#define MESSAGE_SIZE 500
#define READ 0
#define WRITE 1
#define READ2 2
#define WRITE2 3

void createChild(int * fd) {
    pid_t childpid;

    if((childpid = fork()) == -1)
    {
        perror("fork");
        exit(1);
    }
    if(childpid == 0)
    {
        close(fd[WRITE]);
        close(fd[READ2]);

        int value;
        if( read(fd[READ], &value, sizeof(int)) <= 0) {
            perror("Pipe read error.");
            exit(1);
        }
        close(fd[READ]);

        dup2(fd[WRITE2], 1);
        close(fd[WRITE2]);

        // Convert int to string
        char strValue[10];
        sprintf(strValue, "%d", value);

        char *const params[] = {"factorial", strValue, (char*)NULL};
        execv("./factorial", params);
        perror("Factorial exec error.");
        exit(1);
    }
}

int main (void){
    int     fd[4], i;
    char    buffer[MESSAGE_SIZE];

    printf("\nCalculate Factorial\n\n");

    if(pipe(fd) == -1){
        perror("Pipe failed");
        return 1;
    }

    if(pipe(fd + (2)) == -1){
        perror("Pipe2 failed");
        return 1;
    }

    createChild(fd);

    close(fd[READ]);
    close(fd[WRITE2]);

    int num;
    printf("Write a number to calculate factorial: ");
    scanf("%d", &num);

    write(fd[WRITE], &num, sizeof(int));
    close(fd[WRITE]);

    while((i = read(fd[READ2], buffer, MESSAGE_SIZE-1)) > 0) {
        buffer[i] = 0;
        printf("Factorial: %s\n", buffer);
    }

    close(fd[READ2]);
    
    int status;
    wait(&status);
    
    return(0);
}

