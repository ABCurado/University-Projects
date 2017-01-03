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

        char str;

        printf("Message Received: ");

        while(read(fd[0], &str, sizeof(char)) > 0) {
            printf("%c", str);
        }

        printf("\n");

        close(fd[0]);
        exit(0);
    }
    else
    {
        /* Parent process closes up input side of pipe */
        close(fd[0]);

        char str1[] = "Hello world!";
        write(fd[1], str1, (strlen(str1)+1));

        char str2[] = "\nGoodbye!";
        write(fd[1], str2, (strlen(str2)+1));

        close(fd[1]);

        int status;
        wait(&status);
    }
    
    return(0);
}

	