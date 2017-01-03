#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>


void proc_filho(int i){
	int j;
	i++;
	// Calcule to print the numbers needed for each
	for (j = ((i*2)-2)*100000+1; j < ((i*2))*100000+1;j++) {
		printf("Filho %d - %d\n",i,j);
	}
}

int  main(void){
	pid_t pid;
	int i,status;
	// execute the number of times to create new childs
	for (i = 0; i < 6;i++) {
		// create child
		pid = fork();	
		if(pid==0){
			// if child
			proc_filho(i);
			exit(1);	
		}
	}		
	/* Father */
	// Execute the as many as the number of childs
	for(i = 6; i >= 0 ; i--){
		// wait for next child
		wait(&status);
	}

	printf("Todos os filhos terminaram \n");
	return   0;
}