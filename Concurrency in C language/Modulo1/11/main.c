#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>

char cria_gemeos(pid_t lista[2]){
	lista[0] = fork();
	if(lista[0] > 0){
		lista[1] = fork();
		if(lista[1] > 0){
			return 'p';
		}else{
			return 'b';
		}
	}else{
		return 'a';
	}
}

int  main(void){
	pid_t lista[6];
	pid_t pid;
	int status,i;
	char letra;
	for(i = 0; i < 3;i++){
		letra = cria_gemeos(&(lista[i*2]));
		if(letra == 'a'){
			printf("PID: %d Parent PID: %d valor de saida %c\n",getpid(),getppid(),letra);
			exit(1);
		}else if(letra == 'b'){
			printf("PID: %d Parent PID: %d valor de saida %c\n",getpid(),getppid(),letra);
			exit(2);
		}
	}

	for(i = 0; i < 6;i++){
		pid = waitpid(lista[i],&status,0);
		printf("O filho %d terminou com o valor %d\n",pid,WEXITSTATUS(status));
	}
	return 0;
}