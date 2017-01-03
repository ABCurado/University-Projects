#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>

struct Data {
	int  number;
	char  sentence[100];
};

int  main(void){
	pid_t pid;
	struct Data dataRead;
	struct Data dataWrite;
	int status;

	int fd[2];
	if(pipe(fd) == -1){
		perror("Pipe failed");
		return 1;
	}
	if((pid = fork()) == -1){
		perror("fork");
		exit(1);
	}
	if(pid > 0){
		close(fd[0]);
		printf("Number: \n");
		scanf("%d", &(dataWrite.number));
		printf("String: \n");
		getchar();
		fgets(&(dataWrite.sentence),100,stdin);
		write(fd[1],&(dataWrite.number),sizeof(int));
		write(fd[1], &(dataWrite.sentence) ,100);
		close(fd[1]);
	}
	if(pid == 0){
		close(fd[1]);
		if(read(fd[0], &readNum ,sizeof(int)) <= 0) {
			perror("Pipe read error.");
			exit(1);
		}
		printf("Imprimindo %d\n",dataRead.number);
		if(read(fd[0], readChar ,100) <= 0) {
			perror("Pipe read error.");
			exit(1);
		}
		printf("Imprimindo %s\n",dataRead.sentence);
		close(fd[0]);
		exit(0);
	}
	wait(&status);
	printf("Terminou com exit status de %d\n",WEXITSTATUS(status));
	return   0;
}