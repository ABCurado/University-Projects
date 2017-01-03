#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main (void){
	int     fd[2];
    pid_t   childpid;

    pipe(fd);
    
    if((childpid = fork()) == -1)
    {
        perror("fork");
        exit(1);
    }

    if(childpid == 0)
    {
        /* Child process closes up output side of pipe */
        close(fd[1]);

        int value;

        /* Read in a int from the pipe */
        if( read(fd[0], &value, sizeof(value)) <= 0) {
            perror("Pipe read error.");
            exit(1);
        }
        printf("Parent PID: %d\n", value);
        exit(0);
    }
    else
    {
        /* Parent process closes up input side of pipe */
        close(fd[0]);

        int parentPid = getpid();

        /* Send int through the output side of pipe */
        write(fd[1], &parentPid, sizeof(parentPid));
    }
    
    return(0);
}

	