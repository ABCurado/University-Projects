#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <time.h>
#include <sys/wait.h>
#include <stdlib.h>

int  main(void){
	srand(time(NULL));
	int num,temp;
	int pipe01[2];
	int pipe12[2];
	int pipe23[2];
	int pipe34[2];
	int pipe45[2];
	int pipe50[2];

	if(pipe(pipe01) == -1 || pipe(pipe12) == -1 ||pipe(pipe23) == -1||pipe(pipe34) == -1 ||pipe(pipe45) == -1 ||pipe(pipe50) == -1){
		perror("Pipe failed");
		return 1;
	}

	num = rand()%(40);
	printf("Pid:%d, Number:%d(Dad)\n",getpid(), num);
	write(pipe01[1],&num,sizeof(int));
	close(pipe01[1]);

	//Filho 1 
	if(fork() == 0){
		srand(NULL+getpid());
		num = rand()%(40);
		if(read(fd[0], &readNum ,sizeof(int)) <= 0) {
			perror("Pipe read error.");
			exit(1);
		}
		close(pipe01[0]);
		printf("\nPid:%d, Number:%d recebeu \n",getpid(), temp);
		printf("Pid:%d, Number:%d gerou\n",getpid(), num);
		num = num > temp ? num : temp;
		printf("Pid:%d, Number:%d enviou\n\n",getpid(), num);
		write(pipe12[1],&num,sizeof(int));
		close(pipe12[1]);
		exit(0);
	}
	//Filho 2 
	if(fork() == 0){
		srand(NULL+getpid());
		num = rand()%(40);
		if(read(fd[0], &readNum ,sizeof(int)) <= 0) {
			perror("Pipe read error.");
			exit(1);
		}
		close(pipe12[0]);
		printf("Pid:%d, Number:%d recebeu \n",getpid(), temp);
		printf("Pid:%d, Number:%d gerou\n",getpid(), num);
		num = num > temp ? num : temp;
		printf("Pid:%d, Number:%d enviou\n",getpid(), num);
		write(pipe23[1],&num,sizeof(int));
		close(pipe23[1]);
		exit(0);
	}
	//Filho 3 
	if(fork() == 0){
		srand(NULL+getpid());
		num = rand()%(40);
		if(read(fd[0], &readNum ,sizeof(int)) <= 0) {
			perror("Pipe read error.");
			exit(1);
		}	 	
		close(pipe23[0]);
		printf("Pid:%d, Number:%d recebeu \n",getpid(), temp);
		printf("Pid:%d, Number:%d gerou\n",getpid(), num);
		num = num > temp ? num : temp;
		printf("Pid:%d, Number:%d enviou\n",getpid(), num);
		write(pipe34[1],&num,sizeof(int));
		close(pipe34[1]);
		exit(0);
	}
	//Filho 4 
	if(fork() == 0){
		srand(NULL+getpid());
		num = rand()%(40);
		if(read(fd[0], &readNum ,sizeof(int)) <= 0) {
			perror("Pipe read error.");
			exit(1);
		}
		close(pipe34[0]);
		printf("Pid:%d, Number:%d recebeu \n",getpid(), temp);
		printf("Pid:%d, Number:%d gerou\n",getpid(), num);
		num = num > temp ? num : temp;
		printf("Pid:%d, Number:%d enviou\n",getpid(), num);
		write(pipe45[1],&num,sizeof(int));
		close(pipe45[1]);
		exit(0);
	}
		//Filho 5 
	if(fork() == 0){
		srand(NULL+getpid());
		num = rand()%(40);
		if(read(fd[0], &readNum ,sizeof(int)) <= 0) {
			perror("Pipe read error.");
			exit(1);
		}		close(pipe45[0]);
		printf("Pid:%d, Number:%d recebeu \n",getpid(), temp);
		printf("Pid:%d, Number:%d gerou\n",getpid(), num);
		num = num > temp ? num : temp;
		printf("Pid:%d, Number:%d enviou\n",getpid(), num);
		write(pipe50[1],&num,sizeof(int));
		close(pipe50[1]);
		exit(0);
	}

	if(read(fd[0], &readNum ,sizeof(int)) <= 0) {
		perror("Pipe read error.");
		exit(1);
	}
	close(pipe50[0]);
	printf("Pid:%d, Number:%d(Highest Number)\n",getpid(), num);
	return   0;
}