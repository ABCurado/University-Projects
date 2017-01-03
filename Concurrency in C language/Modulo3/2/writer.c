#include <stdio.h>
#include <sys/types.h>
#include <sys/mman.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <sys/stat.h>  
#include <fcntl.h>
#define VEC_SIZE 10

int main(int argc, char *argv[]) {
	int fd, data_size = (VEC_SIZE *sizeof(int));
	int *shared_data;
	if((fd = shm_open("/shmex02", O_CREAT|O_EXCL|O_RDWR, S_IRUSR|S_IWUSR)) < 0){
		perror("shm_open error");
		exit(0);
	}
	if(ftruncate (fd, data_size) != 0){
		perror("ftruncate error");
		exit(0);
	}
	shared_data = (int *)	mmap(NULL, data_size,PROT_WRITE,MAP_SHARED,fd,0);
	shared_data[0]=0;
	shared_data[1]=1;
	shared_data[2]=2;
	shared_data[3]=3;
	shared_data[4]=4;
	shared_data[5]=5;
	shared_data[6]=6;
	shared_data[7]=7;
	shared_data[8]=8;
	shared_data[9]=9;
	return 0;
}