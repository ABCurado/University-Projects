#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <time.h>
#include <sys/wait.h>
#include <stdlib.h>

int  main(void){
	int pipefd[2];
	int i,length = 9;
	char string[length]; 
	if(pipe(pipefd) == -1 ){
		perror("Pipe failed");
		return 1;
	}	

	if(fork() == 0){
		close(pipefd[1]);
		// read from pipe and write to exec
		dup2(pipefd[0],0);
		close(pipefd[0]);
		execlp("more","more",NULL);
		exit(0);
	}

	close(pipefd[0]);
	for(i = 0 ; i < 100; i++){
		// write to pipe the message.
		sprintf(string,"%s%d%s","Linha ",i+1,"\n");
		write(pipefd[1],&string,strlen(string));

	}
	printf("\n\n");
	close(pipefd[1]);
	wait(NULL);
	return   0;
}