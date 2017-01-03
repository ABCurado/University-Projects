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
	int id[CLIENTS];
	int nextToLine;
	int nextToAttend;
}Queue;

int main(int argc, char *argv[] ){
	char semaphoreMutexName[] = "semaphore.mutex";
	char sd_name_ticket[] = "/shm_SCOMP_ex_Ticket";
	char sd_name_queue[] = "/shm_SCOMP_ex_Queue";
	sem_t *semaphoreMutex;
	sem_t *semaphoreVendor;
	sem_t *semaphoreClient;
	int *ticket;
	Queue *queue;
	int nextTicket = 1;
	int fd, sd_size_ticket = sizeof(int), sd_size_queue = sizeof(Queue);

	if((semaphoreMutex = sem_open(semaphoreMutexName, O_CREAT, 0644, 0)) == SEM_FAILED) {
		perror("Error on sem_open()");
		exit(1);
	}
	if((fd = shm_open(sd_name_ticket, O_CREAT|O_RDWR, S_IRUSR|S_IWUSR))<0){
		perror("Error on shm_open.\n");
		exit(1);
	}
	if(ftruncate (fd, sd_size_ticket)!=0) {
		perror("Error on ftruncate.\n");
		exit(1);
	}
	ticket = (int*) mmap(NULL,sd_size_ticket,PROT_READ|PROT_WRITE,MAP_SHARED,fd,0);
	if((fd = shm_open(sd_name_queue, O_CREAT|O_RDWR, S_IRUSR|S_IWUSR))<0){
		perror("Error on shm_open.\n");
		exit(1);
	}
	if(ftruncate (fd, sd_size_queue)!=0) {
		perror("Error on ftruncate.\n");
		exit(1);
	}
	queue = (Queue*) mmap(NULL,sd_size_queue,PROT_READ|PROT_WRITE,MAP_SHARED,fd,0);
	queue->nextToAttend = 0;

	do {
		// Vendor
		char semaphoreVendorName[50];
		char semaphoreClientName[50];

		sem_wait(semaphoreMutex);
		if(queue->nextToAttend != queue->nextToLine) {
			int *id = queue->id + queue->nextToAttend;
			queue->nextToAttend++;
			*ticket = nextTicket;
			nextTicket++;
			sem_post(semaphoreMutex);
			//printf("SERV: wating for client...\n");

			sprintf(semaphoreVendorName, "semaphore.vendor_%d", *id);
			sprintf(semaphoreClientName, "semaphore.client_%d", *id);

			if((semaphoreVendor = sem_open(semaphoreVendorName, O_CREAT, 0644, 0)) == SEM_FAILED) {
				perror("Error on sem_open()");
				exit(1);
			}
			if((semaphoreClient = sem_open(semaphoreClientName, O_CREAT, 0644, 0)) == SEM_FAILED) {
				perror("Error on sem_open()");
				exit(1);
			}

			sem_post(semaphoreVendor);
			printf("SERV: Serving client... Ticket to delivery: %d.\n",nextTicket-1);
			sem_wait(semaphoreClient);
			//printf("SERV: ready for next client...\n");

			//if (sem_close(semaphoreVendor)) {perror("Error closing Semaphore.");exit(1);}
			//if (sem_close(semaphoreClient)) {perror("Error closing Semaphore.");exit(1);}
			//if (sem_unlink(semaphoreVendorName)) {perror("Error unlinking Semaphore.");exit(1);}
			//if (sem_unlink(semaphoreClientName)) {perror("Error unlinking Semaphore.");exit(1);}

		} else {
			sem_post(semaphoreMutex);
			sleep(1);
		}
	}while(1);

	if (munmap(ticket, sd_size_ticket) < 0) {perror("Error unmaping.\n");exit(1);} /* desfaz mapeamento */
	if (shm_unlink(sd_name_ticket) < 0) {perror("Error unlinking.\n");exit(1);} /* remove do sistema */
	
	return 0;
}