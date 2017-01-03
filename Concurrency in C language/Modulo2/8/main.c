#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <time.h>
#include <sys/wait.h>
#include <stdlib.h>

#define SIZE 50000

typedef  struct{
	int codigo_cliente;
	int codigo_produto;
	int quantidade;
}registo;

void preenche_vec(registo * vec){
	int i;
	srand(time(NULL));
	for (i = 0; i < SIZE; i++) {
		vec[i].codigo_cliente = i;
		vec[i].codigo_produto = SIZE-i;
		vec[i].quantidade = (rand()%(40));
	}
}

void proc_filho(int parte, registo * vec,int pipeWrite){
	int j;
	for (j = parte*5000; j < (parte+1)*5000;j++) {
		if(vec[j].quantidade >= 20){
			write(pipeWrite,&(vec[j]),sizeof(registo));
		}
	}
	close(pipeWrite);
	exit(0);
}

int  main(void){
	pid_t pid;
	registo vetor[SIZE];
	registo temp;
	int i;
	preenche_vec(vetor);
	int fd[2];
	int readers[10];
	for(i = 0; i  < 10; i++){
		if(pipe(fd) == -1){
			perror("Pipe failed");
			return 1;
		}
		readers[i] = fd[0];
		if((pid = fork()) == -1){
			perror("fork error");
			exit(1);
		}
		if(pid == 0){
			close(fd[0]);
			proc_filho(i,vetor,fd[1]);
		}
		close(fd[1]);
	}
	registo maiores[SIZE];
	int size;
	for(i = 0; i  < 10; i++){
		while(read(readers[i], &temp ,sizeof(registo))){
			maiores[size] = temp;
			size++;
			printf("%d\n",size);
		}
	}
	for(i = 0; i  < size; i++){
		printf("C_Cliente:%d, C_produto:%d, Qt:%d\n",maiores[i].codigo_cliente,maiores[i].codigo_produto,maiores[i].quantidade);
	}
	close(fd[0]);
	return   0;
}