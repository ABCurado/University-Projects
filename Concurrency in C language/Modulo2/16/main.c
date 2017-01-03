#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <time.h>
#include <sys/wait.h>
#include <stdlib.h>

int  main(int argc, char *argv[] ){
	int pipefd[2];

	// check for 4 agrs
	if (argc != 4){
		printf("Devem ser introduzidos apenas 3 argumentos (programa,programa,ficheiro)");
	}

	if(pipe(pipefd) == -1 ){
		perror("Pipe failed");
		return 1;
	}	

	if(fork() == 0){
		// write to pipe the output of the exec command
		dup2(pipefd[1],1);
		close(pipefd[0]);
		close(pipefd[1]);
		execlp(argv[1],argv[1],NULL);
		perror("Exec falhado");
		exit(0);
	}

	// Open the file with given name
	FILE *fp;
	fp = fopen(argv[3], "w");

	// Read the input from pipe send to pipe
	// Write to file the output of the exec command 
	dup2(pipefd[0],0);
	dup2(fileno(fp),1);
	close(pipefd[0]);
	close(pipefd[1]);
	fclose(fp);
	close(pipefd[0]);
	execlp(argv[2],argv[2],NULL);
	exit(0);

	return   0;
}