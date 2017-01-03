#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
/*
 	Program to test a command that fails and see the return value

*/
char startScript() {
	pid_t script;
	int status;
	// Forks a child to execute the failing script, returns -1
	if ((script=fork()) == 0) {
		int value = -1;
		char *const params[] = {"script.sh", NULL};
		value = execv("./script.sh", params);
		exit(value);
	}
	//Father waits for child to fail and returns the return value
	wait(&status);
	char tmp =  WEXITSTATUS(status);
	return tmp;
}

int main (void){
	printf("\n\nExit value: %d\n\n", startScript());
	return 0;
}