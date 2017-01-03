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
#define BUFFER_SIZE 10

typedef struct{
	int values[BUFFER_SIZE];
	int prodCount;
}Buffer ;

int main(int argc, char *argv[]) {
	int i,fd,out=0;
	Buffer *shared_data;
	sem_t *semaforoFull;
	sem_t *semaforoEmpty;
	sem_t *semaforoMutex;
	if ((semaforoFull = sem_open("ex9.1", O_CREAT, 0644, 0)) == SEM_FAILED) {
		perror("No sem_open()");
		exit(1);
	}
	if ((semaforoEmpty = sem_open("ex9.2", O_CREAT, 0644, BUFFER_SIZE)) == SEM_FAILED) {
		perror("No sem_open()");
		exit(1);
	}
	if ((semaforoMutex = sem_open("ex9.3", O_CREAT, 0644, 1)) == SEM_FAILED) {
		perror("No sem_open()");
		exit(1);
	}
	if((fd = shm_open("/ex9", O_CREAT|O_RDWR, S_IRUSR|S_IWUSR) ) < 0){
		perror("shm_open error");
		exit(0);
	}
	if(ftruncate (fd, sizeof(Buffer))  < 0){
		perror("ftruncate error");
		exit(0);
	}

	shared_data = (Buffer *)mmap(NULL, sizeof(Buffer),PROT_READ|PROT_WRITE,MAP_SHARED,fd,0);

	if(fork()==0){
		for(i = 0 ; i < 30 ; i++){
			sem_wait(semaforoEmpty);
			sem_wait(semaforoMutex);
			shared_data->values[shared_data->prodCount ] = i;
			shared_data->prodCount = (shared_data->prodCount + 1) % BUFFER_SIZE;
			sem_post(semaforoMutex);
			sem_post(semaforoFull);
		}
		exit(1);
	}
	if(fork()==0){
		for(i = 0 ; i < 30 ; i++){
			sem_wait(semaforoEmpty);
			sem_wait(semaforoMutex);
			shared_data->values[shared_data->prodCount ] = i;
			shared_data->prodCount = (shared_data->prodCount + 1) % BUFFER_SIZE;
			sem_post(semaforoMutex);
			sem_post(semaforoFull);
		}
		exit(1);
	}
	for(i = 0 ; i < 30*2 ; i++){
		sem_wait(semaforoFull);
		sem_wait(semaforoMutex);
		printf("Value[%d] = %d\n", out, shared_data->values[out]);
		out = (out + 1) % BUFFER_SIZE;
		sem_post(semaforoMutex);
		sem_post(semaforoEmpty);
	}
	wait(NULL);
	wait(NULL);
	if (sem_unlink("ex9.3")) {
		perror("No sem_unlink() ex9.3");
		exit(1);
	}
	if (sem_unlink("ex9.2")) {
		perror("No sem_unlink() ex9.2");
		exit(1);
	}
	if (sem_unlink("ex9.1")) {
		perror("No sem_unlink() ex9.1");
		exit(1);
	}
	if (munmap(shared_data, sizeof(Buffer)) < 0){
		perror("No munmap");
		exit(1);
	} 
	if (shm_unlink("/ex9") < 0){
		perror("No sem_unlink() shmex9");
		exit(1);
	} 

	return 0;
}

