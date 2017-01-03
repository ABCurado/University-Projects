#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>

int  main(void){
	pid_t pid;
	int i;

	for(i=0;i<3;i++){
		pid=fork ();
		if(pid ==0){
			pid = fork ();
		}
		if(pid >0){
			printf("%d - -%d\n",getppid(),getpid());
			execlp("ls","ls", (char*)NULL)	;
			printf("%d\n",i);
		}
	}
	return 0;
}
