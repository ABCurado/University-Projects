#include <stdio.h>
#include <sys/types.h>
#include <sys/mman.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <time.h>
#include <sys/stat.h>  
#include <fcntl.h>
#define VEC_SIZE 10

void childCode(int pos,int *shared_data,int *vec){
	int i =0;
	shared_data[pos] = 0;
	for(i = pos*100; i < (pos+1)*100;i++){
		if(vec[i] > shared_data[pos]){
			shared_data[pos] = vec[i];
		}
	}
	exit(0);
}


void fillVec(int * vec){
	int i ;
	srand(time(NULL));
	for(i = 0; i < VEC_SIZE*100; i++){
		vec[i] = rand()%(1000);
	}
}


int main(int argc, char *argv[]) {
	int i,status,fd, data_size = (VEC_SIZE *sizeof(int));
	int *shared_data;
	int vec[VEC_SIZE*100];
	if((fd = shm_open("/shmex4", O_CREAT|O_RDWR, S_IRUSR|S_IWUSR) ) < 0){
		perror("shm_open error");
		exit(0);
	}
	if(ftruncate (fd, data_size)  < 0){
		perror("ftruncate error");
		exit(0);
	}
	fillVec(vec);

	shared_data = (int *)mmap(NULL, data_size,PROT_READ|PROT_WRITE,MAP_SHARED,fd,0);

	for(i = 0; i < VEC_SIZE; i++){
		if(fork()==0){
			childCode(i,shared_data,vec);
		}
	}

	for(i = 0; i < VEC_SIZE; i++){
		wait(&status);
	}
	int max = 0;
	for(i = 0; i < VEC_SIZE; i++){
		printf("i = %d : %d\n",i,shared_data[i]);
		if(max < shared_data[i]){
			max = shared_data[i];
		}
	}
	printf("Max number = %d\n",max);

	int r = munmap(shared_data, data_size); /* desfaz mapeamento */
	if (r < 0) exit(1);
	r = shm_unlink("/shmex4"); /* remove do sistema */
	if (r < 0) exit(1);

	return 0;
}


