#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>

int  main(void){
	pid_t   pid;
	int   f;
	for(f=0;f<3;f++){
		pid = fork ();
		if (pid > 0){
			printf("Filho criado\n");
		}else if(pid == -1){
			perror("Fork falhou");exit(1);
		}else{
			sleep (1);
			exit(f);
		}
	}
	
	int status;
	pid = wait(&status);
	printf("O filho com o pid %d terminou com o status %d\n",pid,WEXITSTATUS(status));
	pid = wait(&status);
	printf("O filho com o pid %d terminou com o status %d\n",pid,WEXITSTATUS(status));
	pid = wait(&status);
	printf("O filho com o pid %d terminou com o status %d\n",pid,WEXITSTATUS(status));
	return   0;
}
