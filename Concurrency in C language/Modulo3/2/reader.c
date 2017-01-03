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
	int i,sum,fd, data_size = (VEC_SIZE *sizeof(int));
	int *shared_data;
	if((fd = shm_open("/shmex02", O_RDONLY, S_IRUSR|S_IWUSR)) < 0){
		perror("shm_open error");
		exit(0);
	}
	if(ftruncate (fd, data_size)  > 0){
		perror("ftruncate error!");
		exit(0);
	}
	shared_data = (int *)	mmap(NULL, data_size,PROT_READ,MAP_SHARED,fd,0);
	sum = 0;
	for(i = 0; i < VEC_SIZE;i++){
		printf("vec[%d], contains: %d\n",i,shared_data[i]);
		sum += shared_data[i];
	}
	printf("Sum: %d\n",sum);
	return 0;
}


