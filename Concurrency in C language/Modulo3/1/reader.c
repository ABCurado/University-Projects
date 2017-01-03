#include <fcntl.h> /* Para constantes O_* */
#include <stdio.h>
#include <stdlib.h>
#include "student.h"
#include <sys/mman.h>
#include <sys/stat.h> /* Para constantes de “modo” */
#include <sys/types.h>
#include <unistd.h>

int main(int argc, char *argv[] ){
	char sd_name[] = "/shm_SCOMP_ex1";
	Student *sd;
	int fd, sd_size = sizeof(Student);

	if((fd = shm_open(sd_name, O_EXCL|O_RDWR, S_IRUSR|S_IWUSR))<0){
		perror("(reader): Error on shm_open.\n");
		exit(0);
	}
	if(ftruncate(fd, (off_t)sd_size) < 0) {
		perror("(reader): Error on ftruncate.\n");
		exit(0);
	}
	sd = (Student*)mmap(NULL,sd_size,PROT_READ,MAP_SHARED,fd,0);
	
	printf("\nNumber: %d\n", sd->number);
	printf("Nome: %s\n\n", sd->name);

	int r = munmap(sd, sd_size); /* desfaz mapeamento */
	if (r < 0) exit(1);
	r = shm_unlink(sd_name); /* remove do sistema */
	if (r < 0) exit(1);

	return 0;
}
