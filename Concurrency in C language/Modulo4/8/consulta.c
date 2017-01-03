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
#define PROCESSES 5

typedef struct {
	int id;
	float money;
	int remov;
}Message;

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
	pid_t pid;
	Message *message;
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

	int i = 0;
	for (i = 0; i < PROCESSES; ++i) {

		if((pid=fork())<0){
			perror("Error on fork.\n");
			exit(1);
		} else if (pid == 0) {
			// Vendor
			//printf("SERV: wating for client...\n");
			sem_wait(semaphoreVendor);
			//printf("CLI: Attended by vendor...\n");
			int max = CLIENTS, min = 1;
			srand(time(NULL)+getpid()+i);
			int r = (rand() % max + min);
			message->id = r;
			message->remov = 0;
			sem_post(semaphoreMutex);
			sem_wait(semaphoreMutexReadOnly);
			printf("A conta com id=%d tem %.2f euros.\n", r, message->money);

			sem_post(semaphoreClient);
				
			//printf("SERV: ready for next client...\n");
			exit(0);
		}

	}

	for (i = 0; i < PROCESSES; ++i) {
		wait(NULL); /* esperar que o filho termine */
		// END
	}

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