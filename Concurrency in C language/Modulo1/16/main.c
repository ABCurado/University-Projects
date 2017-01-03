#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>


void removeLastChar(char* string) {
	string[strlen(string)-1] = 0;
}

void execLine(char *str){
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
	exit(0);
}

int  main(){
	pid_t pid;
	char line[100];
	int status;
	
	while(1){
		printf("Program@%d:~$ ",getpid());
		fgets(line, 100, stdin);
		removeLastChar(line);
		if(strcmp(line,"sair") == 0){
			return 0;
		}
		pid=fork ();
		if(pid == 0){
			execLine(line);
		}
		wait(&status);
	}
	
	return 0;
}
