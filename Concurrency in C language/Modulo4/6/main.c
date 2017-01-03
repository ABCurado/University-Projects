#include <stdio.h>
#include <sys/types.h>
#include <sys/mman.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <time.h>
#include <sys/stat.h>  
#include <fcntl.h>
#include <string.h>
#include <semaphore.h>
#define FORK_NUM 5
/**
* Semaforo filho nao é necessario, foi criado a pensar no ex 6
*/
int main(int argc, char *argv[]) {
	int i;
	sem_t *semaforoPai;
	sem_t *semaforoFilho;
	if ((semaforoPai = sem_open("ex5.1", O_CREAT, 0644, 0)) == SEM_FAILED) {
		perror("No sem_open()");
		exit(1);
	}
	if ((semaforoFilho = sem_open("ex5.2", O_CREAT, 0644, 1)) == SEM_FAILED) {
		perror("No sem_open()");
		exit(1);
	}
	
	if(fork() == 0){
		for(i = 0; i < 10 ; i++){
			sem_wait(semaforoPai);
			printf("Eu sou o filho -%d\n",getpid());
			sem_post(semaforoFilho);
		}
		exit(0);
	}
	for(i = 0; i < 10 ; i++){
		sem_wait(semaforoFilho);
		printf("Eu sou o Pai :%d \n",getpid());
		sem_post(semaforoPai);
	}

	wait(NULL);
	if (sem_unlink("ex5.1")) {
		perror("No sem_open()");
		exit(1);
	}
	if (sem_unlink("ex5.2")) {
		perror("No sem_open()");
		exit(1);
	}
	return 0;
}

