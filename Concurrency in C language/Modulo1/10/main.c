#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>

#define SIZE 100000

void preenche_vec(int * vec){
	int i;
	for (i = 0; i < SIZE; i++) {
		*(vec+i) = i;
	}
}

void proc_filho(int parte, int * vec, int valor){
	int j;
	// Calcule for each child to execute his part
	for (j = ((parte*2)-2)*10000; j < ((parte*2))*10000;j++) {
		// If value found
		// return the value number of the child (not the pid)
		if(*(vec+j) == valor){
			printf("Encontrou %d na posição %d\n",valor,j);
			exit(parte);
		}

	}
	// If value not found
	exit(0);
}



int  main(void){
	pid_t pid;
	int i,status ;
	int vetor[SIZE];
	int valor = 79999;
	preenche_vec(vetor);

	// Create number of childs needed
	for (i = 0; i < 5;i++) {
		pid = fork();	
		if(pid==0){
			// Child
			printf("PID: %d Filho criado para a parte %d\n",getpid(),i+1);
			proc_filho(i+1,vetor,valor);	
		}
	}		
	// Father
	// Execute the as many times as the number of childs
	for(i = 0; i < 5 ; i++){
		// Wait for next child
		wait(&status);
		// If result is diferent than zero it was found the number
		if(WEXITSTATUS(status) != 0){
			printf("O numero foi encontrado no processo %d \n",WEXITSTATUS(status));
		}
	}
	printf("Todos os filhos terminaram \n");
	return   0;
}