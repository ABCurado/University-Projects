#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>

// prints string
void M(char * stringToPrint){
	printf("%s\n",stringToPrint);
}

void codigoPai(){
	pid_t pid;
	// prints
	M("A");
	// create new child
	pid = fork();
	if(pid == 0){
		// If child prints B and create new child
		M("B");
		pid = fork();
		if(pid > 0){
			// if father of the fork made above
			// prints
			M("A");
		}
	}else{
		// else if father
		M("A");
	}
}

void codigoPrimeiroFilho(){
	pid_t pid;
	// prints
	M("B");	
	// create new child
	pid = fork();
	if(pid > 0){
		//if father
		M("A");
	}else{
		// else if new child
		// creates new child
		pid = fork();
		if(pid > 0){
			// if father of the fork made above
			// prints
			M("A");
		}else{
			// else if child of fork made above
			// prints
			M("B");
			// create new child
			pid = fork();
			if(pid > 0){
				// if father of the fork made above
				// prints
				M("A");
			}
		}
	}
}

int  main(void){
	//Create first child
	pid_t pid;   
	pid = fork ();

	// Separate child from father
	if(pid == 0){
		codigoPrimeiroFilho();
	}else{
		codigoPai();
	}
	return   0;

}