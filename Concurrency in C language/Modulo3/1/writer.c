#include <fcntl.h> /* Para constantes O_* */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "student.h"
#include <sys/mman.h>
#include <sys/stat.h> /* Para constantes de “modo” */
#include <sys/types.h>
#include <unistd.h>

int main(int argc, char *argv[] ){
	char sd_name[] = "/shm_SCOMP_ex1";
	Student *sd;
	int fd, sd_size = sizeof(Student);

	if((fd = shm_open(sd_name, O_CREAT|O_EXCL|O_RDWR, S_IRUSR|S_IWUSR))<0){
		perror("(writer): Error on shm_open.\n");
		exit(0);
	}
	if(ftruncate (fd, sd_size)<0) {
		perror("(writer): Error on ftruncate.\n");
		exit(0);
	}
	sd = (Student*)mmap(NULL,sd_size,PROT_READ|PROT_WRITE,MAP_SHARED,fd,0);
	
	sd->number = 646464;
	strcpy(sd->name, "Quim Bala");
	
	return 0;
}
