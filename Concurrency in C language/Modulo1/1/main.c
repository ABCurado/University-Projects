#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>

int  main(void){
	pid_t p, a;

	p = fork ();
	if(p == -1){
		perror("Fork falhou");exit(1);
	}
	a = fork ();
	if(a == -1){
		perror("Fork falhou");exit(1);
	}
	printf("Sistemas  de  Computadores\n");

	return  0;
}
