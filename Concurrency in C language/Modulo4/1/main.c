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
#define FORK_NUM 5

int main(int argc, char *argv[]) {
	int i,j;
	FILE *fp;
	if((fp = fopen("file.txt", "wr")) == NULL){
		perror("No file open");
		exit(1);
	}

	sem_t *semaforo;
	if ((semaforo = sem_open("ex1", O_CREAT, 0644, 1)) == SEM_FAILED) {
		perror("No sem_open()");
		exit(1);
	}
	int status = 5;
	sem_getvalue(semaforo, &status);
	printf("%d\n",status);
	for(i = 0; i < FORK_NUM; i++){
		if(fork() == 0){
			printf("Filho %d entra em wait\n",getpid());
			sem_wait(semaforo);
			printf("Filho %d sai de wait\n",getpid());
			for(j = i*100; j < (i+1)*100;j++ ){
				fprintf(fp, "%d\n",j );
			}
			sem_post(semaforo);
			exit(0);
		}
		printf("Filho %d criado\n",i);
	}
	for(i = 0; i < FORK_NUM; i++){
		wait(NULL);
	}

	if (sem_unlink("ex1") == -1) {
		perror("No sem_unlink()");
		exit(1);
	}
	fclose(fp);
	execlp("cat","cat","file.txt",NULL);
	return 0;
}

