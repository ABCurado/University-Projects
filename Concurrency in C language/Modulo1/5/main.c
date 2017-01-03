#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>


int  main(void){
	fork ();
	printf("1\n");
	fork ();
	printf("2\n");
	fork ();
	printf("3\n");
	return   0;

}