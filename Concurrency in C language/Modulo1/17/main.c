/*
	The c function "system" uses the operating system
	to execute the command given by parameter. The os
	handles the creation of the proccess.
*/

/*
	We can bennefit from the "system" function if we 
	just want to execute programs despite of what they
	do and their results, the only returned value is
	an int representing the if the commands was completed
	or not.
	With forks, we can handle better low level code and
	we can manipulate more variables then we could with
	"system" function.

	A: Depending on what you want to do, sometimes forks
	   will be better, other times you can use system.
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

void removeLastChar(char* string) {
	string[strlen(string)-1] = 0;
}

void proprietarySystem(char *str){
	char ** res  = NULL;
	char *  p    = strtok (str, " ");
	int n_spaces = 0;
	while (p) {
		res = realloc (res, sizeof (char*) * ++n_spaces);
		if (res == NULL) {
			exit (-1);
		}
		res[n_spaces-1] = p;
		p = strtok (NULL, " ");

	}
	res = realloc (res, sizeof (char*) * (n_spaces+1));
	res[n_spaces] = 0;
	execvp(res[0],res);
	printf("\n");
	free (res);
	exit(-1);
}


int main (int argc, char **argv){
	pid_t pid;
	char line[100];
	int status;

	printf("Program: ");
	fgets(line, 100, stdin);
	removeLastChar(line);
	pid=fork ();
	if(pid == 0){
		proprietarySystem(line);
	}
	wait(&status);
	return 0;
}