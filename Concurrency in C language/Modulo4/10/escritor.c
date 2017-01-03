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

#define PROCESSES 50
#define CHARSIZE 100

typedef struct {
	char message[CHARSIZE];
}Message;

int main(int argc, char *argv[] ){
	char semaphoreWriteCountName[] = "semaphore.semaphoreWriteCount";
	char semaphoreReadCountName[] = "semaphore.semaphoreReadCount";
	char semaphoreReadersName[] = "semaphore.semaphoreReaders";
	char semaphoreWrittersName[] = "semaphore.semaphoreWritters";
	char semaphoreReaderPriorityName[] = "semaphore.semaphoreReaderPriority";
	char sd_name_writeCount[] = "/shm_SCOMP_ex_writeCount";
	char sd_name_readCount[] = "/shm_SCOMP_ex_readCount";
	char sd_name_message[] = "/shm_SCOMP_ex_message";
	sem_t *semaphoreWriteCount;
	sem_t *semaphoreReadCount;
	sem_t *semaphoreReaders;
	sem_t *semaphoreWritters;
	sem_t *semaphoreReaderPriority;
	pid_t pid;
	int *writeCount;
	int *readCount;
	Message *message;
	int fd, sd_size_writeCount = sizeof(int), sd_size_readCount = sizeof(int), sd_size_message = sizeof(Message);

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
	if((semaphoreReaderPriority = sem_open(semaphoreReaderPriorityName, O_CREAT, 0644, 1)) == SEM_FAILED) {
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
	if((fd = shm_open(sd_name_message, O_CREAT|O_RDWR, S_IRUSR|S_IWUSR))<0){
		perror("Error on shm_open.\n");
		exit(1);
	}
	if(ftruncate (fd, sd_size_message)!=0) {
		perror("Error on ftruncate.\n");
		exit(1);
	}
	message = (Message*) mmap(NULL,sd_size_message,PROT_READ|PROT_WRITE,MAP_SHARED,fd,0);
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
			//if(*writeCount == 1) {
				//sem_wait(semaphoreReaders);		// while writing disallow reading
			//}
			sem_post(semaphoreWriteCount);		// writeCount semaphone

			sem_wait(semaphoreReaderPriority);		// Give reader priority

			sem_wait(semaphoreWritters);		// allow 1 writer
			// WRITE DATA
			char info[100];

			time_t current_time;
    		char* c_time_string;
    		current_time = time(NULL);
		    /* Convert to local time format. */
		    c_time_string = ctime(&current_time);
			sprintf(info, "PID: %d on %s", getpid(), c_time_string);
			
			strcat(message->message, info);
			sem_wait(semaphoreReadCount);
			sem_wait(semaphoreWriteCount);
			printf("ESCRITOR: Escritores: %d\tLeitores: %d\n", *writeCount, *readCount);
			sem_post(semaphoreWriteCount);
			sem_post(semaphoreReadCount);
			// STOP WRITE DATA

			sem_post(semaphoreWritters);		// allow next writer

			sem_post(semaphoreReaderPriority);		// reader priority

			sem_wait(semaphoreWriteCount);		// writeCount semaphone
			*writeCount -= 1;
			//if(*writeCount == 0) {
			//sem_post(semaphoreReaders);		// after writing allow reading
			//}
			sem_post(semaphoreWriteCount);		// writeCount semaphone
			
			exit(0);
		}

	}

	for (i = 0; i < PROCESSES; ++i) {
		wait(NULL); /* esperar que o filho termine */
		// END
	}

	if (munmap(message, sd_size_message) < 0) {perror("Error unmaping.\n");exit(1);} /* desfaz mapeamento */
	//if (shm_unlink(sd_name_message) < 0) {perror("Error unlinking.\n");exit(1);} /* remove do sistema */
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