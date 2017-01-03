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

void criaServidor(int num,sem_t *semaforoServidor[]){
	srand(time(NULL));
	int i=0;
	struct timespec ts;
	if (clock_gettime(CLOCK_REALTIME, &ts) == -1) {
		perror("clock_gettime");
		exit(EXIT_FAILURE);
	}
	ts.tv_sec += 5;
	while(1){
		sem_timedwait(semaforoServidor[0],&ts);
		if(errno == ETIMEDOUT){
			printf("Servidor %d a fechar serviço devido á falta de clientes\n",num+1);
			exit(0);
		}
		printf("Servidor %d a trabalhar no %dº trabalho\n",num+1,i);
		sleep(rand() % 10);
		sem_post(semaforoServidor[1]);
		ts.tv_sec += 3;
		i++;
	}
}


void criarClientes(int num, sem_t *semaforoServidor[],sem_t *semaforoMutex,sem_t *semaforoBuffer){
	int i;
	int flag;
	for(i = 0 ; i < 30; i++){
		if(fork() == 0){

			sem_wait(semaforoMutex);
			sem_getvalue(semaforoBuffer,&flag);
			sem_trywait(semaforoBuffer);
			if( errno == EAGAIN){
				printf("%d -> Fila de espera cheia, indo embora:%d \n",getpid(),i);
				sem_post(semaforoMutex);
				exit(0);
			}
			sem_post(semaforoMutex);

			sem_post(semaforoServidor[0]);
			sem_wait(semaforoServidor[1]);

			printf("%d : Foi Servido\n",getpid());
			
			sem_wait(semaforoMutex);
			sem_post(semaforoBuffer);
			sem_post(semaforoMutex);
			exit(0);
		}
	}
	for(i = 0 ; i < 30 ; i++){
		wait(NULL);
	}
	exit(0);
}


int main(int argc, char *argv[]) {
	int i;
	sem_t *semaforoBuffer;
	sem_t *semaforoMutex;
	char name[50];
	sem_t *semaforoServidor[6];
	//Inicializando semaforos
	for(i = 0; i < 3; i++){
		sprintf(name,"%s%d","ex12.",i+1);
		if ((semaforoServidor[i] = sem_open(name, O_CREAT, 0644, 0)) == SEM_FAILED) {
			perror("No sem_open()");
			exit(1);
		}
	}
	if ((semaforoBuffer = sem_open("ex12.9", O_CREAT, 0644, BUFFER_SIZE)) == SEM_FAILED) {
		perror("No sem_open()");
		exit(1);
	}
	if ((semaforoMutex = sem_open("ex12.8", O_CREAT, 0644, 1)) == SEM_FAILED) {
		perror("No sem_open()");
		exit(1);
	}
	i=0;
	//Cria Servidores
	for(i = 0; i < 3; i++){
		if(fork()==0){
			criaServidor(i,semaforoServidor);
		}
	}
	//Cria Produtor de Cliente
	if(fork()==0){
		criarClientes(i,semaforoServidor,semaforoMutex,semaforoBuffer);
	}

	wait(NULL);
	wait(NULL);
	wait(NULL);
	wait(NULL);
	//Unlink
	for(i = 0; i < 3; i++){
		sprintf(name,"%s%d","ex12.",i+1);
		if (sem_unlink(name)) {
			printf("No sem_unlink()%s  ",name);
			perror("Error()\n");
			exit(1);
		}
	}
	if (sem_unlink("ex12.9")) {
		perror("No sem_unlink() ex12.9");
		exit(1);
	}
	if (sem_unlink("ex12.8")) {
		perror("No sem_unlink() ex12.8");
		exit(1);
	}
	return 0;
}

