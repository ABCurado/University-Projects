#include <fcntl.h> /* Para constantes O_* */
#include <stdio.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <sys/stat.h> /* Para constantes de “modo” */
#include <sys/types.h>
#include <unistd.h>

#define REPEATS 100000

int main(int argc, char *argv[] ){
	char sd_name[] = "/shm_SCOMP_ex3";
	int *sd;
	int fd, sd_size = sizeof(int);
	pid_t pid;

	if((fd = shm_open(sd_name, O_CREAT|O_EXCL|O_RDWR, S_IRUSR|S_IWUSR))<0){
		printf("Error on shm_open.\n");
	}
	if(ftruncate (fd, sd_size)!=0) {
		printf("Error on ftruncate.\n");
	}
	sd = (int*)mmap(NULL,sd_size,PROT_READ|PROT_WRITE,MAP_SHARED,fd,0);
	*sd = 100;

	if((pid=fork())<0){
		printf("Error on fork.\n");
	} else if (pid == 0) {
		// on Child
		int i;
		for (i = 0; i < REPEATS; ++i) {
			(*sd)++;
			(*sd)--;
			printf("%d\n", *sd);
		}
		exit(0);
	}
	
	int i;
	for (i = 0; i < REPEATS; ++i) {
		(*sd)++;
		(*sd)--;
		printf("%d\n", *sd);
	}

	int status;
	wait(&status);

	int r = munmap(sd, sd_size); /* desfaz mapeamento */
	if (r < 0) exit(1);
	r = shm_unlink(sd_name); /* remove do sistema */
	if (r < 0) exit(1);

	return 0;
}
