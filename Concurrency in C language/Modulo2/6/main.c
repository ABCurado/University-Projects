#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>

#define SIZE 1000

void preenche_vec(int * vec){
	int i;
	for (i = 0; i < SIZE; i++) {
		*(vec+i) = i;
	}
}

void proc_filho(int parte, int * vec1, int * vec2,int pipeWrite){
	int j,sum;
	for (j = parte*200; j < (parte+1)*200;j++) {
		sum = *(vec1+j) + *(vec2+j);
		write(pipeWrite,&sum,sizeof(int));
	}
	close(pipeWrite);
	exit(0);
}

int  main(void){
	pid_t pid;
	int vetor1[SIZE];
	int vetor2[SIZE];
	int i,sum,readNum;
	preenche_vec(vetor1);
	preenche_vec(vetor2);
	int fd[2];
	int readers[5];
	for(i = 0; i  < 5; i++){
		if(pipe(fd) == -1){
			perror("Pipe failed");
			return 1;
		}
		readers[i] = fd[0];
		if((pid = fork()) == -1){
			perror("fork");
			exit(1);
		}
		if(pid == 0){
			close(fd[0]);
			proc_filho(i,vetor1,vetor2,fd[1]);
		}
		close(fd[1]);
	}
	for(i = 0; i  < 5; i++){
		while(read(readers[i], &readNum ,sizeof(int)))
			sum+=readNum;
	}
	close(fd[0]);
	printf("A soma é %d\n",sum);
	return   0;
}