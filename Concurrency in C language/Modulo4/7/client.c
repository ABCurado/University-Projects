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
	pid_t pid;
	int *ticket;
	Queue *queue;
	int fd, sd_size_ticket = sizeof(int), sd_size_queue = sizeof(Queue);

	if((semaphoreMutex = sem_open(semaphoreMutexName, O_CREAT, 0644, 1)) == SEM_FAILED) {
		perror("Error on sem_open()");
		exit(1);
	}
	if((fd = shm_open(sd_name_ticket, O_CREAT|O_RDWR, S_IRUSR|S_IWUSR))<0){
		printf("Error on shm_open.\n");
	}
	if(ftruncate (fd, sd_size_ticket)!=0) {
		printf("Error on ftruncate.\n");
	}
	ticket = (int*) mmap(NULL,sd_size_ticket,PROT_READ,MAP_SHARED,fd,0);
	if((fd = shm_open(sd_name_queue, O_CREAT|O_RDWR, S_IRUSR|S_IWUSR))<0){
		printf("Error on shm_open.\n");
	}
	if(ftruncate (fd, sd_size_queue)!=0) {
		printf("Error on ftruncate.\n");
	}
	queue = (Queue*) mmap(NULL,sd_size_queue,PROT_READ|PROT_WRITE,MAP_SHARED,fd,0);
	queue->nextToLine = 0;

	int i = 0;
	for (i = 0; i < CLIENTS; ++i)
	{
		if((pid=fork())<0){
			perror("Error on fork.\n");
			exit(1);
		} else if (pid == 0) {
			// Cliente
			char semaphoreVendorName[50];
			char semaphoreClientName[50];
			
			sprintf(semaphoreVendorName, "semaphore.vendor_%d", getpid());
			sprintf(semaphoreClientName, "semaphore.client_%d", getpid());

			if((semaphoreVendor = sem_open(semaphoreVendorName, O_CREAT, 0644, 0)) == SEM_FAILED) {
				perror("Error on sem_open()");
				exit(1);
			}
			if((semaphoreClient = sem_open(semaphoreClientName, O_CREAT, 0644, 0)) == SEM_FAILED) {
				perror("Error on sem_open()");
				exit(1);
			}

			sem_wait(semaphoreMutex);
			printf("%d got here.\n", getpid());
			queue->id[queue->nextToLine] = getpid();
			queue->nextToLine++;
			sem_post(semaphoreMutex);
			
			sem_wait(semaphoreVendor);
			//printf("CLI: Attended by vendor...\n");
			int max = 3, min = 2;
			srand(time(NULL)+getpid()+i);
			sleep(rand() % max + min);
			int myTicket = *ticket;
			sem_post(semaphoreClient);
			//printf("");
			printf("I'm %d, and I got the ticket number: %d.\n", getpid(), myTicket);
			
			if (sem_close(semaphoreVendor)) {perror("Error closing semaphore.vendor.");exit(1);}
			if (sem_close(semaphoreClient)) {perror("Error closing semaphore.client.");exit(1);}
			if (sem_unlink(semaphoreVendorName)) {perror("Error unlinking semaphore.vendor.");exit(1);}
			if (sem_unlink(semaphoreClientName)) {perror("Error unlinking semaphore.client.");exit(1);}

			exit(0);
		}

	}

	for (i = 0; i < CLIENTS; ++i) {
		wait(NULL); /* esperar que os filhos terminem */
	}

	if (munmap(ticket, sd_size_ticket) < 0) {perror("Error unmaping.\n");exit(1);} /* desfaz mapeamento */
	if (shm_unlink(sd_name_ticket) < 0) {perror("Error unlinking.\n");exit(1);} /* remove do sistema */
	if (munmap(queue, sd_size_queue) < 0) {perror("Error unmaping.\n");exit(1);} /* desfaz mapeamento */
	if (shm_unlink(sd_name_queue) < 0) {perror("Error unlinking.\n");exit(1);} /* remove do sistema */
	//if (sem_close(semaphore)) {perror("Error closing Semaphore.");exit(1);}
	if (sem_close(semaphoreMutex)) {perror("Error closing semaphore.mutex.");exit(1);}
	if (sem_unlink(semaphoreMutexName)) {perror("Error unlinking semaphore.mutex.");exit(1);}
			
	return 0;
}