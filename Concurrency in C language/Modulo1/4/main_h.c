#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>

int  main(void){
	pid_t   pid,pid2;
	int   f;
	for(f=0;f<3;f++){
		pid = fork ();
		if (pid > 0){
			printf("Filho criado\n");
		}else if(pid == -1){
			perror("Fork falhou");exit(1);
		}else{
			break;
		}
	}
	int status;
	if(pid == 0){
		printf("FILHO PID - %d EXITSTATUS - %d\n", getpid(), f);
		exit(f);
	}else{
		do{			
			pid2 = waitpid(pid2, &status, WNOHANG);
		}while(pid2 == -1);
		printf("PAI: O Filho %d com o PID %d terminou \n", WEXITSTATUS(status), pid );
	}
	return   0;
}
