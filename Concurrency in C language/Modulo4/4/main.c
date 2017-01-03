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

#define REPEAT 1

void removeFirstLine(char * filePath) {
	char newFile[] = "tmp.txt";

	char command[100] = "bash -c \"tail -n +2 ";
	strcat(command, filePath);
	strcat(command, " > ");
	strcat(command, newFile);
	strcat(command, "\"");
	// command = "bash -c \"tail -n +2 filePath > newFile\"
   	system(command);

   	remove(filePath);
	rename(newFile, filePath);
}

void verifyMoreThan10Lines(char * filePath) {
	char ch;
	int number_of_lines=0;
	FILE *pFile = fopen(filePath, "r");
	do
	{
	    ch = fgetc(pFile);
	    if(ch == '\n') {
	    	number_of_lines++;
	    }
	}while (ch != EOF);
	fclose(pFile);
	printf("LINES: %d\n", number_of_lines);
	if(number_of_lines>=10) {
		printf("File has more 10 or more lines.\n");
		removeFirstLine(filePath);
	}
}

int main(int argc, char *argv[] ){
	char semaforoName[] = "semaforo";
	sem_t *semaforo;
	struct timespec ts;
	pid_t pid;
	int i;
	char * filePath;

	if(argc!=2) {
		perror("Number of arguments should be 1.");
		exit(1);
	}

	filePath = argv[1];
	if (clock_gettime(CLOCK_REALTIME, &ts)) {
        perror("Cannot define real time.");
        exit(1);
    }
	ts.tv_sec += 10;

	if ((semaforo = sem_open(semaforoName, O_CREAT, 0644, 1)) == SEM_FAILED) {
		perror("No sem_open()");
		exit(1);
	}

	for (i = 0; i < REPEAT; ++i)
	{
		if((pid=fork())<0){
			perror("Error on fork.\n");
			exit(1);
		} else if (pid == 0) {

			while(sem_timedwait(semaforo, &ts)) {
				perror("\nNão foi possivel aceder ao ficheiro. Waited more than 10 seconds. ");
				exit(1);
			}

			verifyMoreThan10Lines(filePath);

			FILE *pFile = fopen(filePath, "a");
			if(pFile==NULL) {
			    perror("Error opening file.");
			}
			//WRITE TO FILE
			fprintf(pFile, "Eu sou o processo com o PID %d\n", getpid());
			printf("Wrote: \"Eu sou o processo com o PID %d\".\n\n", getpid());
			sleep(2);
			fclose(pFile);
			sem_post(semaforo);
			exit(0);
		}

		wait(NULL); /* esperar que o filho termine */
		// END
	}

	/*if(sem_close(semaforo)) {
		perror("Error closing Semaforo.");
		exit(1);
	}
	if (sem_unlink(semaforoName)) {
		perror("Error unlinking Semaforo.");
		exit(1);
	}*/

	return 0;
}