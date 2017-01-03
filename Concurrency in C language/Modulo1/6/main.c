#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>

// a)
int main(void){
	pid_t pid;
	int resultados[50000];
	int dados[100000];
	int i;

	// Give random values to array
	for (i = 0; i < 100000; i++) {
		dados[i] = rand() % 100 + 1;
		printf("%d\n",dados[i]);
	}

	//Create child
	pid = fork ();

	if(pid == 0){
		// Run the first part of the array
		for (i = 0; i < 50000; i++) {
			resultados[i] = dados[i] * 4 + 20;
			printf("i - %d %d\n",i,resultados[i]);
		}
	}else{
		// Run the second part of the array
		for (i = 0; i < 50000; i++) {
			resultados[i] = dados[49999+i] * 4 + 20;
			printf("i - %d %d\n",i,resultados[i]);
		}
	}
	return 0;
}
