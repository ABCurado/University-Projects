#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <time.h>
#include <sys/wait.h>
#include <stdlib.h>

int  main(void){
	int pipefd[2];
	if(pipe(pipefd) == -1 ){
		perror("Pipe failed");
		return 1;
	}	
	FILE *file;
	char buff;
	file =fopen("fx.txt","r");
	if (!file)
		return -1;

	if(fork() == 0){
		close(pipefd[1]);
		// Read pipe and send to exec
		dup2(pipefd[0],0);
		close(pipefd[0]);
		execlp("od","od",NULL);
		exit(0);
	}

	close(pipefd[0]);
	// Read char from file ends when received EOF
	while ((buff = fgetc(file))!= EOF){
		printf("%c",buff);
		write(pipefd[1],&buff,sizeof(char));

	}
	printf("\n\n"); 
	close(pipefd[1]);
	return   0;
}