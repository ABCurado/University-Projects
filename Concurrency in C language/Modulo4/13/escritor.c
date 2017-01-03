#include <fcntl.h>		/* Para constantes O_* */
#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/mman.h>
#include <sys/stat.h>	/* Para constantes de “modo” */
#include <sys/types.h>
#include <time.h>		// time()
#include <unistd.h>
#include <sys/wait.h>

#define PROCESSES 50
#define MESSAGESIZE 100
#define QUEUESIZE 3

typedef struct {
	char message[MESSAGESIZE];
}Message;

typedef struct {
	int queue1Count;
	Message queue1[QUEUESIZE];
	int queue2Count;
	Message queue2[QUEUESIZE];
	int queue3Count;
	Message queue3[QUEUESIZE];
	int queue4Count;
	Message queue4[QUEUESIZE];
}Queue;

int main(int argc, char *argv[] ){
	char semaphoreWriteCountName[] = "semaphore.semaphoreWriteCount";
	char semaphoreReadCountName[] = "semaphore.semaphoreReadCount";
	char semaphoreReadersName[] = "semaphore.semaphoreReaders";
	char semaphoreWrittersName[] = "semaphore.semaphoreWritters";
	char semaphoreQueue1Name[] = "semaphore.semaphoreQueue1";
	char semaphoreQueue2Name[] = "semaphore.semaphoreQueue2";
	char semaphoreQueue3Name[] = "semaphore.semaphoreQueue3";
	char semaphoreQueue4Name[] = "semaphore.semaphoreQueue4";
	char sd_name_writeCount[] = "/shm_SCOMP_ex_writeCount";
	char sd_name_readCount[] = "/shm_SCOMP_ex_readCount";
	char sd_name_queue[] = "/shm_SCOMP_ex_queue";
	sem_t *semaphoreWriteCount;
	sem_t *semaphoreReadCount;
	sem_t *semaphoreReaders;
	sem_t *semaphoreWritters;
	pid_t pid;
	int *writeCount;
	int *readCount;
	Queue *queues;
	int fd, sd_size_writeCount = sizeof(int), sd_size_readCount = sizeof(int), sd_size_queue = sizeof(Queue);

	if((semaphoreWriteCount = sem_open(semaphoreWriteCountName, O_CREAT, 0644, 1)) == SEM_FAILED) {
		perror("Error on sem_open()");
		exit(1);
	}
	if((semaphoreReadCount = sem_open(semaphoreReadCountName, O_CREAT, 0644, 1)) == SEM_FAILED) {
		perror("Error on sem_open()");
		exit(1);
	}
	if((semaphoreReaders = sem_open(semaphoreReadersName, O_CREAT, 0644, 1)) == SEM_FAILED) {
		perror("Error on sem_open()");
		exit(1);
	}
	if((semaphoreWritters = sem_open(semaphoreWrittersName, O_CREAT, 0644, 1)) == SEM_FAILED) {
		perror("Error on sem_open()");
		exit(1);
	}
	if((semaphoreQueue1 = sem_open(semaphoreQueue1Name, O_CREAT, 0644, QUEUESIZE)) == SEM_FAILED) {
		perror("Error on sem_open()");
		exit(1);
	}
	if((semaphoreQueue2 = sem_open(semaphoreQueue2Name, O_CREAT, 0644, QUEUESIZE)) == SEM_FAILED) {
		perror("Error on sem_open()");
		exit(1);
	}
	if((semaphoreQueue3 = sem_open(semaphoreQueue3Name, O_CREAT, 0644, QUEUESIZE)) == SEM_FAILED) {
		perror("Error on sem_open()");
		exit(1);
	}
	if((semaphoreQueue4 = sem_open(semaphoreQueue4Name, O_CREAT, 0644, QUEUESIZE)) == SEM_FAILED) {
		perror("Error on sem_open()");
		exit(1);
	}
	if((fd = shm_open(sd_name_writeCount, O_CREAT|O_RDWR, S_IRUSR|S_IWUSR))<0){
		perror("Error on shm_open.\n");
		exit(1);
	}
	if(ftruncate (fd, sd_size_writeCount)!=0) {
		perror("Error on ftruncate.\n");
		exit(1);
	}
	writeCount = (int*) mmap(NULL,sd_size_writeCount,PROT_READ|PROT_WRITE,MAP_SHARED,fd,0);
	*writeCount = 0;
	if((fd = shm_open(sd_name_readCount, O_CREAT|O_RDWR, S_IRUSR|S_IWUSR))<0){
		perror("Error on shm_open.\n");
		exit(1);
	}
	if(ftruncate (fd, sd_size_readCount)!=0) {
		perror("Error on ftruncate.\n");
		exit(1);
	}
	readCount = (int*) mmap(NULL,sd_size_readCount,PROT_READ|PROT_WRITE,MAP_SHARED,fd,0);
	if((fd = shm_open(sd_name_queue, O_CREAT|O_RDWR, S_IRUSR|S_IWUSR))<0){
		perror("Error on shm_open.\n");
		exit(1);
	}
	if(ftruncate (fd, sd_size_queue)!=0) {
		perror("Error on ftruncate.\n");
		exit(1);
	}
	queues = (Queue*) mmap(NULL,sd_size_queue,PROT_READ|PROT_WRITE,MAP_SHARED,fd,0);
	sem_wait(semaphoreReaders);
	strcpy(message->message, "");
	sem_post(semaphoreReaders);

	int i = 0;
	for (i = 0; i < PROCESSES; ++i) {

		if((pid=fork())<0){
			perror("Error on fork.\n");
			exit(1);
		} else if (pid == 0) {

			sem_wait(semaphoreWriteCount);		// writeCount semaphone
			*writeCount += 1;			
			if(*writeCount == 1) {
				sem_wait(semaphoreReaders);		// while writing disallow reading
			}
			sem_post(semaphoreWriteCount);		// writeCount semaphone

			sem_wait(semaphoreWritters);		// allow 1 writer

			// WRITE DATA
			char info[100];

			int max = 4, min = 1;
			srand(time(NULL)+getpid()+i);
			int priority = rand() % max + min;
			switch(priority) {
				case 1: {
					if()
					queues->queue1Count;
					Message* message = queues->queue1
					message->message;
					break;}
				case 2: {break;}
				case 3: {break;}
				case 4: {break;}
			}
			// STOP WRITE DATA

			sem_post(semaphoreWritters);		// allow next writer

			sem_wait(semaphoreWriteCount);		// writeCount semaphone
			*writeCount -= 1;
			if(*writeCount == 0) {
				sem_post(semaphoreReaders);		// after writing allow reading
			}
			sem_post(semaphoreWriteCount);		// writeCount semaphone
			
			exit(0);
		}

	}

	for (i = 0; i < PROCESSES; ++i) {
		wait(NULL); /* esperar que o filho termine */
		// END
	}

	if (munmap(message, sd_size_queue) < 0) {perror("Error unmaping.\n");exit(1);} /* desfaz mapeamento */
	//if (shm_unlink(sd_name_queue) < 0) {perror("Error unlinking.\n");exit(1);} /* remove do sistema */
	if (munmap(writeCount, sd_size_writeCount) < 0) {perror("Error unmaping.\n");exit(1);} /* desfaz mapeamento */
	if (shm_unlink(sd_name_writeCount) < 0) {perror("Error unlinking.\n");exit(1);} /* remove do sistema */
	if (munmap(readCount, sd_size_readCount) < 0) {perror("Error unmaping.\n");exit(1);} /* desfaz mapeamento */
	//if (shm_unlink(sd_name_readCount) < 0) {perror("Error unlinking.\n");exit(1);} /* remove do sistema */
	if (sem_close(semaphoreWriteCount)) {perror("Error closing semaphore.");exit(1);}
	if (sem_close(semaphoreReadCount)) {perror("Error closing semaphore.");exit(1);}
	if (sem_close(semaphoreWritters)) {perror("Error closing semaphore.");exit(1);}
	if (sem_close(semaphoreReaders)) {perror("Error closing semaphore.");exit(1);}
	//if (sem_unlink(semaphoreWriteCountName)) {perror("Error unlinking semaphore.");exit(1);}
	//if (sem_unlink(semaphoreReadCountName)) {perror("Error unlinking semaphore.");exit(1);}
	//if (sem_unlink(semaphoreWrittersName)) {perror("Error unlinking semaphore.");exit(1);}
	//if (sem_unlink(semaphoreReadersName)) {perror("Error unlinking semaphore.");exit(1);}
	
	return 0;
}