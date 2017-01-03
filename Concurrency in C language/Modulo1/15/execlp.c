#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>

int  main(int argc, char *argv[] ){
	pid_t pid;
	int i,status;
	printf("Argumetos:	 %d\n",argc);
	
	for(i=1;i<argc;i++){
		pid=fork ();
		if(pid == 0){
			printf("%s\n",argv[i]);
			execlp(argv[i],argv[i], (char*)NULL);
			exit(0);
		}
	}
	for(i=1;i<argc;i++){
		wait(&status);
	}
	
	return 0;
}
