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

void createChild(int * fd, char * file) {
    pid_t childpid;

    if((childpid = fork()) == -1)
    {
        perror("fork");
        exit(1);
    }
    if(childpid == 0)
    {
        close(fd[READ]);
        dup2(fd[WRITE], 1);
        close(fd[WRITE]);

        char *const params[] = {"sort", file, (char*)NULL};
        execvp("sort", params);
        perror("Exec error.");
        exit(1);
    }
}

int main (void){
    int     fd[2], i;
    char    buffer[MESSAGE_SIZE];
    char    file[] = "fx.txt";

    printf("\nSort %s file.\n\n", file);

    if(pipe(fd) == -1){
        perror("Pipe failed");
        return 1;
    }

    createChild(fd, file);

    close(fd[WRITE]);

    while((i = read(fd[READ], buffer, MESSAGE_SIZE-1)) > 0) {
        buffer[i] = 0;
        printf("%s", buffer);
    }

    close(fd[READ]);
    
    int status;
    wait(&status);
    
    return(0);
}

