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
/**
* Solução talvez simplificada de mais para o problema
* A Entrada quando o parque esta vazio pode nao funcionar por prioridade 
* de chegada esta depende do SO 
*/

void criarAlunos(sem_t *semaforoPrioridade[],sem_t *semaforoSize,int fila[],sem_t *semaforoMutex){
	int i;
	for(i = 0 ; i < 15; i++){
		if(fork() == 0){
			sem_wait(semaforoMutex);
			fila[2]++;	
			sem_post(semaforoMutex);
			printf("O aluno %d chegou\n",getpid());
			sem_wait(semaforoPrioridade[2]);
			sem_wait(semaforoMutex);
			fila[2]--;	
			sem_post(semaforoMutex);
			printf("O aluno %d entrou\n",getpid());
			sleep(5);
			printf("O aluno %d saiu\n",getpid());

			sem_post(semaforoSize);
			exit(0);
		}
	}
	for(i = 0 ; i < 15 ; i++){
		wait(NULL);
	}
	printf("%d - %d - %d\n\n\n",fila[0],fila[1],fila[2] );
	exit(0);
}

void criarFuncionarios(sem_t *semaforoPrioridade[],sem_t *semaforoSize,int fila[],sem_t *semaforoMutex){
	int i;
	for(i = 0 ; i < 15; i++){
		if(fork() == 0){
			sem_wait(semaforoMutex);
			fila[1]++;	
			sem_post(semaforoMutex);
			printf("O funcionario %d chegou\n",getpid());
			sem_wait(semaforoPrioridade[1]);
			sem_wait(semaforoMutex);
			fila[1]--;	
			sem_post(semaforoMutex);
			printf("O funcionario %d entrou\n",getpid());
			sleep(5);
			printf("O funcionario %d saiu\n",getpid());

			sem_post(semaforoSize);
			exit(0);
		}
	}
	for(i = 0 ; i < 15 ; i++){
		wait(NULL);
	}
		printf("%d - %d - %d\n\n\n",fila[0],fila[1],fila[2] );
	exit(0);
}


void criarDocentes(sem_t *semaforoPrioridade[],sem_t *semaforoSize,int fila[],sem_t *semaforoMutex){
	int i;
	for(i = 0 ; i < 15; i++){
		if(fork() == 0){
			sem_wait(semaforoMutex);
			fila[0]++;	
			sem_post(semaforoMutex);
			printf("O docente %d chegou\n",getpid());
			sem_wait(semaforoPrioridade[0]);
			printf("O docente %d entrou\n",getpid());
			sem_wait(semaforoMutex);
			fila[0]--;
			sem_post(semaforoMutex);
			sleep(5);
			printf("O docente %d saiu\n",getpid());

			sem_post(semaforoSize);
			exit(0);
		}
	}
	for(i = 0 ; i < 15 ; i++){
		wait(NULL);
	}
		printf("%d - %d - %d\n\n\n",fila[0],fila[1],fila[2] );
	exit(0);
}

void criaCancela(sem_t *semaforoPrioridade[],sem_t *semaforoSize,int fila[3],sem_t *semaforoMutex){
	while(1){
		if((fila[0] != 0 || fila[1] != 0 || fila[2] != 0) ){
			sem_wait(semaforoSize);
			if(fila[0] != 0){
				sem_post(semaforoPrioridade[0]);
			}else if(fila[1] != 0){
				sem_post(semaforoPrioridade[1]);
			}else if(fila[2] != 0){
				sem_post(semaforoPrioridade[2]);
			}
		}
	}
	exit(0);
}

int main(int argc, char *argv[]) {
	sem_t *semaforoPrioridade[3];
	sem_t *semaforoSize;
	sem_t *semaforoMutex;
	int i,fd,*fila;
	char name[256];
	//Inicializando semaforos

	if ((semaforoPrioridade[0] = sem_open("/ex16.1", O_CREAT, 0644, 0)) == SEM_FAILED) {
		perror("No sem_open()");
		exit(1);
	}
	if ((semaforoPrioridade[1] = sem_open("/ex16.2", O_CREAT, 0644, 0)) == SEM_FAILED) {
		perror("No sem_open()");
		exit(1);
	}
	if ((semaforoPrioridade[2] = sem_open("/ex16.3", O_CREAT, 0644, 0)) == SEM_FAILED) {
		perror("No sem_open()");
		exit(1);
	}
	if ((semaforoSize = sem_open("/ex16.4", O_CREAT, 0644, BUFFER_SIZE)) == SEM_FAILED) {
		perror("No sem_open()");
		exit(1);
	}
	if ((semaforoMutex = sem_open("/ex16.5", O_CREAT, 0644, 1)) == SEM_FAILED) {
		perror("No sem_open()");
		exit(1);
	}

	if((fd = shm_open("ex16.6", O_CREAT|O_RDWR, S_IRUSR|S_IWUSR))<0){
		printf("Error on shm_open.\n");
	}
	if(ftruncate (fd, 3) < 0) {
		printf("Error on ftruncate.\n");
	}
	fila = (int*) mmap(NULL,3,PROT_READ|PROT_WRITE,MAP_SHARED,fd,0);

	fila[0]=0;
	fila[1]=0;
	fila[2]=0;	
	printf("Cria cancela\n");
	if(fork() == 0){
		criaCancela(semaforoPrioridade,semaforoSize,fila,semaforoMutex);
	}
	printf("Cria docentes\n");
	if(fork() == 0){
		criarDocentes(semaforoPrioridade,semaforoSize,fila,semaforoMutex);
	}
	printf("Cria funcionarios\n");
	if(fork() == 0){
		criarFuncionarios(semaforoPrioridade,semaforoSize,fila,semaforoMutex);
	}
	printf("Cria alunos\n");
	if(fork() == 0){
		criarAlunos(semaforoPrioridade,semaforoSize,fila,semaforoMutex);
	}



	for(i = 0; i < 4; i++){
		wait(NULL);
	}
	printf("Terminou");

	int r = munmap(fila, 3); /* desfaz mapeamento */
	if (r < 0) exit(1);

	//Unlink
	for(i = 0; i < 6 ; i++){
		snprintf(name, sizeof name, "%s%d", "ex16.",i+1);
		if (sem_unlink(name)) {

			perror("No sem_unlink()");                     
			exit(1);
		}
	}

	return 0;
}

