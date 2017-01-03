#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>
int  main(void){
	pid_t pid;
	int status;
	char readChar;
	char writeChar;
	FILE *fp;
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
		fp = fopen("file.txt","r");
		while( ( writeChar = fgetc(fp) ) != EOF )
			write(fd[1],&writeChar,sizeof(char));
		close(fd[1]);
	}
	if(pid == 0){
		close(fd[1]);
		while(read(fd[0], &readChar ,sizeof(char)))
			printf("%c",readChar);
		printf("\n");
		close(fd[0]);
		exit(0);
	}
	wait(&status);
	printf("Terminou com exit status de %d\n",WEXITSTATUS(status));
	return   0;
}