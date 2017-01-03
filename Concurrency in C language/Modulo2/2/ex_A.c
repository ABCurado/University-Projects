#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>
int  main(void){
	pid_t pid;
	int status;
	int readNum;
	int writeNum;
	char readChar[100];
	char writeChar[100];
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
		scanf("%d", &writeNum);
		printf("String: \n");
		getchar();
		fgets(writeChar,100,stdin);
		write(fd[1],&writeNum,sizeof(int));
		write(fd[1], writeChar ,100);
		close(fd[1]);
	}
	if(pid == 0){
		close(fd[1]);
		if(read(fd[0], &readNum ,sizeof(int)) <= 0) {
			perror("Pipe read error.");
			exit(1);
		}
		printf("Imprimindo %d\n",readNum);
		if(read(fd[0], readChar ,100) <= 0) {
			perror("Pipe read error.");
			exit(1);
		}
		printf("Imprimindo %s\n",readChar);
		close(fd[0]);
		exit(0);
	}
	wait(&status);
	printf("Terminou com exit status de %d\n",WEXITSTATUS(status));
	return   0;
}