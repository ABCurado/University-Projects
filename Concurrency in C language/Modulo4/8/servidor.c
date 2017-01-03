#include <fcntl.h> /* Para constantes O_* */
#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/mman.h>
#include <sys/stat.h> /* Para constantes de “modo” */
#include <sys/types.h>
#include <time.h>    // time()
#include <unistd.h>
#include <sys/wait.h>

#define CLIENTS 10

typedef struct {
	int id;
	float money;
}Client;

typedef struct {
	int id;
	float money;
	int remov;
}Message;

void createAccounts(Client * accounts) {
	int i = 0;
	for (i = 0; i < CLIENTS; ++i) {
		(accounts + i)->id = i+1;
		int max = 100, min = 10;
		srand(time(NULL)+i);
		(accounts + i)->money = (rand() % max + min);
	}
}

int main(int argc, char *argv[] ){
	char semaphoreVendorName[] = "semaphore.vendor";
	char semaphoreClientName[] = "semaphore.client";
	char semaphoreMutexName[] = "semaphore.mutex";
	char semaphoreMutexReadOnlyName[] = "semaphore.mutexreadonly";
	char sd_name[] = "/shm_SCOMP_ex";
	sem_t *semaphoreVendor;
	sem_t *semaphoreClient;
	sem_t *semaphoreMutex;
	sem_t *semaphoreMutexReadOnly;
	Client account[CLIENTS];
	Message *message;
	//int *queue;
	int fd, sd_size = sizeof(Message);

	if((semaphoreVendor = sem_open(semaphoreVendorName, O_CREAT, 0644, 0)) == SEM_FAILED) {
		perror("Error on sem_open()");
		exit(1);
	}
	if((semaphoreClient = sem_open(semaphoreClientName, O_CREAT, 0644, 0)) == SEM_FAILED) {
		perror("Error on sem_open()");
		exit(1);
	}
	if((semaphoreMutex = sem_open(semaphoreMutexName, O_CREAT, 0644, 0)) == SEM_FAILED) {
		perror("Error on sem_open()");
		exit(1);
	}
	if((semaphoreMutexReadOnly = sem_open(semaphoreMutexReadOnlyName, O_CREAT, 0644, 0)) == SEM_FAILED) {
		perror("Error on sem_open()");
		exit(1);
	}
	if((fd = shm_open(sd_name, O_CREAT|O_RDWR, S_IRUSR|S_IWUSR))<0){
		perror("Error on shm_open.\n");
		exit(1);
	}
	if(ftruncate (fd, sd_size)!=0) {
		perror("Error on ftruncate.\n");
		exit(1);
	}
	message = (Message*) mmap(NULL,sd_size,PROT_READ|PROT_WRITE,MAP_SHARED,fd,0);
	
	createAccounts(account);
	message->id = -1;
	message->money = 0.0;
	message->remov = 0;

	do {
		
		//printf("SERV: Waiting for client.\n");
		
		sem_post(semaphoreVendor);
		//printf("SERV: Wating for client information.\n");
		sem_wait(semaphoreMutex);
		//printf("SERV: Preparing info for client.\n");
		int id = message->id;
		//printf("Get ID -> %d\n",id);
		if(message->remov==1) {
			if(account[id].money >= message->money) {
				//printf("Remove Money\n");
				account[id].money -= message->money;
				message->remov = 1;
			} else {
				//printf("Impossible to Remove Money\n");
				message->remov = 0;
			}
		} else {
			//printf("Read money %.2f\n",account[id].money);
			message->money = account[id].money;
		}
		sem_post(semaphoreMutexReadOnly);
		printf("SERV: Info is ready for client. Info about account num: %d.\n",id);
		sem_wait(semaphoreClient);
		
	}while(1);

	if (munmap(message, sd_size) < 0) {perror("Error unmaping.\n");exit(1);} /* desfaz mapeamento */
	if (shm_unlink(sd_name) < 0) {perror("Error unlinking.\n");exit(1);} /* remove do sistema */
	if (sem_close(semaphoreClient)) {perror("Error closing semaphore.client.");exit(1);}
	if (sem_close(semaphoreVendor)) {perror("Error closing semaphore.client.");exit(1);}
	if (sem_close(semaphoreMutex)) {perror("Error closing semaphore.client.");exit(1);}
	if (sem_close(semaphoreMutexReadOnly)) {perror("Error closing semaphore.client.");exit(1);}
	if (sem_unlink(semaphoreClientName)) {perror("Error unlinking semaphore.vendor.");exit(1);}
	if (sem_unlink(semaphoreVendorName)) {perror("Error unlinking semaphore.vendor.");exit(1);}
	if (sem_unlink(semaphoreMutexName)) {perror("Error unlinking semaphore.vendor.");exit(1);}
	if (sem_unlink(semaphoreMutexReadOnlyName)) {perror("Error unlinking semaphore.vendor.");exit(1);}
	
	return 0;
}