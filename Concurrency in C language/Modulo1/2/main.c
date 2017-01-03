#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>
/*

*/

int  main(void){
	pid_t pid1, pid2;
	int  status;
	pid1 = fork();
	if(pid1 == 0){
		printf("Eu sou o 1º filho é %d \n",getpid());
		sleep(5);
		exit(1);
	}
	pid2 = fork();
	if(pid2 == 0){
		printf("Eu sou o 2º filho é %d \n",getpid());
		exit(2);
	}
	printf("EU SOU O PAI e o meu processo é %d \n",getpid());
	pid1 = waitpid(pid1,&status,0);
	printf("Pai: o 1º filho (%d) retornou o valor:%d\n", pid1, WEXITSTATUS(status));
	pid2 = waitpid(pid2,&status,0);
	printf("Pai: o 2º filho (%d) retornou o valor:%d\n", pid2, WEXITSTATUS(status));	
	return  0;
}
