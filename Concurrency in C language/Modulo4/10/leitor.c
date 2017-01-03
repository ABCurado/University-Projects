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
	char semaphoreReadCountName[] = "semaphore.semaphoreReadCount";
	char semaphoreCriticSectionName[] = "semaphore.semaphoreCriticSection";
	char semaphoreReadersName[] = "semaphore.semaphoreReaders";
	char semaphoreWrittersName[] = "semaphore.semaphoreWritters";
	char semaphoreReaderPriorityName[] = "semaphore.semaphoreReaderPriority";
	char sd_name_readCount[] = "/shm_SCOMP_ex_readCount";
	char sd_name_message[] = "/shm_SCOMP_ex_message";
	sem_t *semaphoreReadCount;
	sem_t *semaphoreCriticSection;
	sem_t *semaphoreReaders;
	sem_t *semaphoreWritters;
	sem_t *semaphoreReaderPriority;
	pid_t pid;
	int *readCount;
	Message *message;
	int fd, sd_size_readCount = sizeof(int), sd_size_message = sizeof(Message);

	if((semaphoreReadCount = sem_open(semaphoreReadCountName, O_CREAT, 0644, 1)) == SEM_FAILED) {
		perror("Error on sem_open()");
		exit(1);
	}
	if((semaphoreCriticSection = sem_open(semaphoreCriticSectionName, O_CREAT, 0644, 1)) == SEM_FAILED) {
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
	if((fd = shm_open(sd_name_readCount, O_CREAT|O_RDWR, S_IRUSR|S_IWUSR))<0){
		perror("Error on shm_open.\n");
		exit(1);
	}
	if(ftruncate (fd, sd_size_readCount)!=0) {
		perror("Error on ftruncate.\n");
		exit(1);
	}
	readCount = (int*) mmap(NULL,sd_size_readCount,PROT_READ|PROT_WRITE,MAP_SHARED,fd,0);
	*readCount = 0;
	if((fd = shm_open(sd_name_message, O_CREAT|O_RDWR, S_IRUSR|S_IWUSR))<0){
		perror("Error on shm_open.\n");
		exit(1);
	}
	if(ftruncate (fd, sd_size_message)!=0) {
		perror("Error on ftruncate.\n");
		exit(1);
	}
	message = (Message*) mmap(NULL,sd_size_message,PROT_READ|PROT_WRITE,MAP_SHARED,fd,0);
	
	int i = 0;
	for (i = 0; i < PROCESSES; ++i) {

		if((pid=fork())<0){
			perror("Error on fork.\n");
			exit(1);
		} else if (pid == 0) {

			sem_wait(semaphoreCriticSection); 	// Not allowing to try to access to critic section
			sem_wait(semaphoreReaders);			// Only 1 reader access to critic memory
			sem_wait(semaphoreReadCount);		// readCount semaphone
			*readCount += 1;
			if(*readCount == 1) {
				sem_wait(semaphoreReaderPriority);
				sem_wait(semaphoreWritters);	// while reading not allow write
			}

			sem_post(semaphoreReadCount);		// readCount semaphone
			sem_post(semaphoreReaders);			// Allowing next reader to access to critic memory
			sem_post(semaphoreCriticSection); 	// Allowing to try to access to critic semaphoneRead
			// READ DATA
			sem_wait(semaphoreReadCount);		// readCount semaphone
			printf("\nLEITOR: Leitores: %d\nString: \n%s\n", *readCount, ""/*message->message*/);
			sem_post(semaphoreReadCount);		// readCount semaphone
			// STOP READ DATA

			sem_wait(semaphoreReadCount);		// readCount semaphone
			*readCount -= 1;
			if(*readCount == 0) {
				sem_post(semaphoreWritters);	// After all reading allow write
				sem_post(semaphoreReaderPriority);
			}
			sem_post(semaphoreReadCount);		// readCount semaphone
			
			exit(0);
		}

	}

	for (i = 0; i < PROCESSES; ++i) {
		wait(NULL); /* esperar que o filho termine */
		// END
	}

	if (munmap(message, sd_size_message) < 0) {perror("Error unmaping.\n");exit(1);} /* desfaz mapeamento */
	if (shm_unlink(sd_name_message) < 0) {perror("Error unlinking.\n");exit(1);} /* remove do sistema */
	if (munmap(readCount, sd_size_readCount) < 0) {perror("Error unmaping.\n");exit(1);} /* desfaz mapeamento */
	if (shm_unlink(sd_name_readCount) < 0) {perror("Error unlinking.\n");exit(1);} /* remove do sistema */
	if (sem_close(semaphoreReadCount)) {perror("Error closing semaphore.");exit(1);}
	if (sem_close(semaphoreCriticSection)) {perror("Error closing semaphore.");exit(1);}
	if (sem_close(semaphoreWritters)) {perror("Error closing semaphore.");exit(1);}
	if (sem_close(semaphoreReaders)) {perror("Error closing semaphore.");exit(1);}
	if (sem_unlink(semaphoreReadCountName)) {perror("Error unlinking semaphore.");exit(1);}
	if (sem_unlink(semaphoreCriticSectionName)) {perror("Error unlinking semaphore.");exit(1);}
	if (sem_unlink(semaphoreWrittersName)) {perror("Error unlinking semaphore.");exit(1);}
	if (sem_unlink(semaphoreReadersName)) {perror("Error unlinking semaphore.");exit(1);}
		
	return 0;
}