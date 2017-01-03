#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <time.h>
#include <unistd.h>

#define VEC_SIZE 1000

void fillVec(int * vec) {
    int i;
    int min = 1, max = 10;
    //printf("\n\nVEC: ");
    for (i = 0; i < VEC_SIZE; ++i) {
        srand(time(NULL)+i);
        vec[i] = rand() % max + min;
        //printf("%d ", vec[i]);
    }
}

void childReadVec(int * pipe, int * vec1, int * vec2, int childNum) {
    pid_t childpid;

    if((childpid = fork()) == -1)
    {
        perror("fork");
        exit(1);
    }
    if(childpid == 0)
    {
        int sum = 0;
        int i;

        close(pipe[0]);

        for (i = childNum*VEC_SIZE/5; i < childNum*VEC_SIZE/5+VEC_SIZE/5; ++i) {
            sum += vec1[i] + vec2[i];
        }

        write(pipe[1], &sum, sizeof(sum));

        close(pipe[1]);
        exit(0);
    }
}

int main (void){
    int     vec1[VEC_SIZE], vec2[VEC_SIZE];
	int     fd[2];

    fillVec(vec1);
    fillVec(vec2);

    pipe(fd);

    // Avoid error
    printf(" ");

    int i;
    for (i = 0; i < 5; ++i) {
        childReadVec(fd, vec1, vec2, i);
    }
    
    /* Parent process closes up input side of pipe */
    close(fd[1]);

    int sum=0, value;

    while (read(fd[0], &value, sizeof(value)) > 0) {
        sum += value;
    }

    close(fd[0]);

    printf("\nFinal sum = %d\n", sum);

    int status;
    wait(&status);
    wait(&status);
    wait(&status);
    wait(&status);
    wait(&status);
    
    return(0);
}

	