#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <time.h>
#include <unistd.h>

#define MESSAGE_SIZE 50
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

        char command[MESSAGE_SIZE];
        int i;
        while((i = read(fd[READ], &command, MESSAGE_SIZE)) > 0) {
            command[i] = 0;
        }
        
        close(fd[READ]);

        dup2(fd[WRITE2], 1);
        close(fd[WRITE2]);

        char *const params[] = {"bash", "-c", command, (char*)NULL};
        execvp("bash", params);
        perror("Bash exec error.");
        exit(1);
    }
}

int main (void){
    int     fd[4], i;
    char    buffer[MESSAGE_SIZE];

    printf("\nExecute bash command\n\n");

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

    char command[MESSAGE_SIZE];
    printf("Exec command: ");
    fgets(command, MESSAGE_SIZE, stdin);
    command[MESSAGE_SIZE-1]=0;
    fflush(stdin);
    fflush(stdout);

    write(fd[WRITE], &command, MESSAGE_SIZE);
    close(fd[WRITE]);

    printf("Command result:\n");
    while((i = read(fd[READ2], buffer, MESSAGE_SIZE)) > 0) {
        buffer[i] = 0;
        printf("%s", buffer);
    }

    close(fd[READ2]);
    
    int status;
    wait(&status);
    
    return(0);
}

