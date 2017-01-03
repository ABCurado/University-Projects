#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <time.h>
#include <unistd.h>

#define CHILD_CREATE 10
#define MESSAGE_SIZE 6
#define READ 0
#define WRITE 1

typedef struct {
    char    message[MESSAGE_SIZE];
    int     winRound;
}winner;

void createChild(int * pipe, size_t size) {
    pid_t childpid;

    if((childpid = fork()) == -1)
    {
        perror("fork");
        exit(1);
    }
    if(childpid == 0)
    {
        close(pipe[WRITE]);

        winner win;

        if( read(pipe[READ], &win, size) <= 0) {
            perror("Pipe read error.");
            exit(1);
        }

        printf("Mensagem: %s. Sou o filho: %d. E acabei em: %d.\n",
                                    win.message, getpid(), win.winRound);

        close(pipe[READ]);
        exit(0);
    }
}

int main (void){
    int     fd[2];
    winner win;

    printf("\nCorrida ao pipe.\n\n");

    if(pipe(fd) == -1){
        perror("Pipe failed");
        return 1;
    }

    int i;
    for (i = 0; i < CHILD_CREATE; ++i) {
        createChild(fd, sizeof(win));
    }
    
    /* Parent process closes up input side of pipe */
    close(fd[READ]);

    pid_t finishOrder[10];

    for (i = 0; i < CHILD_CREATE; ++i) {
        winner win;
        strcpy(win.message, "Win!");
        win.winRound = i+1;
        write(fd[WRITE], &win, sizeof(win));

        int status;
        finishOrder[i] = wait(&status);

        sleep(2); //2 seconds
    }
    
    printf("\n");
    close(fd[WRITE]);

    for (i = 0; i < CHILD_CREATE; ++i) {
        printf("Order %d, process: %d.\n", i+1, finishOrder[i]);
    }
    
    return(0);
}

    