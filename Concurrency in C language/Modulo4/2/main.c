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
	char name[50];
	sem_t *semaforo[5];
	for(i = 0; i < FORK_NUM; i++){
		sprintf(name,"%s%d","ex2.",i+1);
		if ((semaforo[i] = sem_open(name, O_CREAT, 0644, 0)) == SEM_FAILED) {
			perror("No sem_open()");
			exit(1);
		}

	}
	sem_post((semaforo[0]));
	for(i = 0; i < FORK_NUM; i++){
		if(fork() == 0){
			printf("Filho %d entra em wait\n",getpid());
			sem_wait(semaforo[i]);
			printf("Filho %d sai de wait\n",getpid());
			for(j = i*100; j < (i+1)*100;j++ ){
				fprintf(fp, "%d\n",j );
			}
			if(i < FORK_NUM-1){
				sem_post(semaforo[i+1]);
			}
			exit(0);
		}
		printf("Filho %d criado\n",i);
	}
	for(i = 0; i < FORK_NUM; i++){
		wait(NULL);
	}

	for(i = 0; i < FORK_NUM; i++){
		sprintf(name,"%s%d","ex2.",i+1);
		if (sem_unlink(name)) {
			perror("No sem_open()");
			exit(1);
		}
	}
	fclose(fp);
	execlp("cat","cat","file.txt",NULL);
	return 0;
}

