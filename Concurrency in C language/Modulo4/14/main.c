#include <stdio.h>
#include <sys/types.h>
#include <sys/mman.h>
#include <time.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <time.h>
#include <sys/stat.h>  
#include <fcntl.h>
#include <errno.h>
#include <string.h>
#include <semaphore.h>
#define BUFFER_SIZE 10




void criarClientes(sem_t *semaforoEntrada,sem_t *semaforoSaida,sem_t *semaforoFila){
	int i;
	for(i = 0 ; i < 15; i++){
		if(fork() == 0){
			sem_post(semaforoFila);
			printf("%d: Na fila de espera\n",getpid());
			sem_wait(semaforoEntrada);
			printf("%d: Vendo a exposiçao\n",getpid());
			sem_wait(semaforoSaida);
			printf("%d: Indo embora\n",getpid());
			exit(0);
		}
	}
	for(i = 0 ; i < 15 ; i++){
		wait(NULL);
	}
	exit(0);
}

void criaSalaExposicoes(sem_t *semaforoEntrada,sem_t *semaforoSaida,sem_t *semaforoFila){
	int val,i,flag;

	while((flag++) < 5){
		sem_getvalue(semaforoFila,&val);
		printf("Estão %d pessoas na fila\n",val);
		printf("A exposição começou\n");

		if(val > 5){
			for(i = 0; i < 5; i++){
				sem_post(semaforoEntrada);
			}
		}else{
			for(i = 0; i < val; i++){
				sem_post(semaforoEntrada);
			}
		}
		sleep(5);

		if(val > 5){
			for(i = 0; i < 5; i++){
				sem_post(semaforoSaida);
				sem_wait(semaforoFila);
			}
		}else{
			for(i = 0; i < val; i++){
				sem_post(semaforoSaida);
				sem_wait(semaforoFila);
			}
		}
		printf("Acabou\n");
		sleep(5);
	}

	exit(0);
}

int main(int argc, char *argv[]) {
	sem_t *semaforoEntrada;
	sem_t *semaforoSaida;
	sem_t *semaforoFila;
	//Inicializando semaforos
	if ((semaforoEntrada = sem_open("/ex14.1", O_CREAT, 0644, 0)) == SEM_FAILED) {
		perror("No sem_open()");
		exit(1);
	}
	if ((semaforoSaida = sem_open("/ex14.2", O_CREAT, 0644, 0)) == SEM_FAILED) {
		perror("No sem_open()");
		exit(1);
	}
	if ((semaforoFila = sem_open("/ex14.3", O_CREAT, 0644, 0)) == SEM_FAILED) {
		perror("No sem_open()");
		exit(1);
	}
	if(fork() == 0){
		criaSalaExposicoes(semaforoEntrada,semaforoSaida,semaforoFila);
	}
	//Cria Produtor de Cliente
	if(fork() == 0){
		criarClientes(semaforoEntrada,semaforoSaida,semaforoFila);
	}

	wait(NULL);
	wait(NULL);

	//Unlink
	if (sem_unlink("/ex14.1")) {
		perror("No sem_unlink() ex14.1");                     
		exit(1);
	}
	if (sem_unlink("/ex14.2")) {
		perror("No sem_unlink() ex14.2");
		exit(1);
	}
	if (sem_unlink("/ex14.3")) {
		perror("No sem_unlink() ex14.3");
		exit(1);
	}

	return 0;
}

