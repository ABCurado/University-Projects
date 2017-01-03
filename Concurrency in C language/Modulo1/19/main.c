#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>

char destinationFolder[8] = "backup/";

void createDestinationFolder() {
	pid_t createFolder;
	int status;
	if ((createFolder=fork()) == 0) {
		int value;
		printf("\n*** IGNORE ERRORS HERE ***\n");
		char *const params[] = {"mkdir", destinationFolder, NULL};
		value = execvp("mkdir", params);
		exit(value);
	}
	wait(&status);
	printf("**************************\n");
}

void copyToBackup(char* pathToFile) {
	pid_t copyProccess;
	int status;
	if ((copyProccess=fork()) == 0) { // IF CHILD
		createDestinationFolder();
		int value;
		char *const params[] = {"cp", pathToFile, destinationFolder, NULL};
		value = execvp("cp", params);
		exit(value);
	}
	wait(&status);
}

void removeLastChar(char* string) {
	string[strlen(string)-1] = 0;
}


void run() {
	char repeat[10];
	do {
		char pathToFile[100];
		printf("\nPath of the file to copy: ");
		fgets(pathToFile, 100, stdin);
		removeLastChar(pathToFile);
		copyToBackup(pathToFile);
		do {
			printf("\nWant to copy more files?(y/n): ");
			fgets(repeat, 10, stdin);
			removeLastChar(repeat);
		}while(strcmp(repeat,"y") != 0 && strcmp(repeat,"n") != 0 &&
			   strcmp(repeat,"yes") != 0 && strcmp(repeat,"no") != 0 &&
			   strcmp(repeat,"s") != 0 && strcmp(repeat,"nao") != 0 &&
			   strcmp(repeat,"sim") != 0);
	}while(strcmp(repeat,"n") != 0 || strcmp(repeat,"no") != 0 ||
		   strcmp(repeat,"nao") != 0);
}

int main (void){
	run();
	return 0;
}