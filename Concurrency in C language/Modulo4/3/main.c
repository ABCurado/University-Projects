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

#define REPEAT 3

int main(int argc, char *argv[] ){
	char semaforoName[] = "semaforo";
	sem_t *semaforo;
	pid_t pid;
	int i;
	char * filePath;

	if(argc!=2) {
		perror("Number of arguments should be 1.");
		exit(1);
	}

	filePath = argv[1];

	if ((semaforo = sem_open(semaforoName, O_CREAT|O_EXCL, 0644, 1)) == SEM_FAILED) {
		perror("No sem_open()");
		exit(1);
	}

	for (i = 0; i < REPEAT; ++i)
	{
		if((pid=fork())<0){
			perror("Error on fork.\n");
			exit(1);
		} else if (pid == 0) {
			sem_wait(semaforo);
			FILE *pFile = fopen(filePath, "a");
			if(pFile==NULL) {
			    perror("Error opening file.");
			}
			//WRITE TO FILE
			fprintf(pFile, "Eu sou o processo com o PID %d\n", getpid());
			sleep(2);
			fclose(pFile);
			sem_post(semaforo);
			exit(0);
		}

		wait(NULL); /* esperar que o filho termine */
		// END
	}

	/*if (sem_unlink(semaforoName)) {
		perror("Error unlinking Shared Memory.");
		exit(1);
	}*/

	return 0;
}