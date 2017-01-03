#include  <string.h>
#include  <unistd.h>
#include  <sys/wait.h>
#include  <stdio.h>
#include  <stdlib.h>

int   main(){
	int i, estado, erro, fd[2];
	pid_t pid;
	char mens[100], ler[100];
	pipe(fd); //Creates a pipe 
	dup2(fd[0],0); //Points standart input to the pipe's start
	dup2(fd[1],1); //Points standart output to the pipe's start
	close(fd[0]); //Closes the descriptor's start
	close(fd[1]); //Closes the descriptor's end
	for(i=1;i<5;i++){
		pipe(fd); //for each cycle run creates a new pipe
		pid = fork(); 
		
		if(pid > 0)
			erro = dup2(fd[1],   1); // If is the parent rebases standart output to the new pipe's start
		else
			erro = dup2(fd[0],   0); // If is the child rebases standart input to the new pipe's start

		close(fd[0]); //Closes the descriptor's start
		close(fd[1]); //Closes the descriptor's end

		if(pid)
			break; // Parent breaks off the cycle
	}
	// Concatenates the string on mens
	sprintf(mens,"Este é o processo %d com a identificacao %d e o seu pai é %d\n",i,(int)getpid(),(int)getppid());
	// Writes the string message to the standart output 
	write(1, mens, strlen(mens) + 1);
	// Reads a message from the standart input
	read(0, ler, strlen(mens));
	//waits for his only child  (if its the last child it wont wait)
	wait(&estado);
	// Prints the received message
	fprintf(stderr, "Processo actual = %d, dados -> %s", (int)getpid(), ler);

	exit(0);
}