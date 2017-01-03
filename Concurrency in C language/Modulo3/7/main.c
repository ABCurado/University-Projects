#include <fcntl.h> /* Para constantes O_* */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/mman.h>
#include <sys/stat.h> /* Para constantes de “modo” */
#include <sys/types.h>
#include <time.h>    // time()
#include <unistd.h>
#include <sys/wait.h>

#define BUFFER_SIZE 10
#define EXCHAN_VALUES 30

typedef struct {
	int counter;
	int values[BUFFER_SIZE];
}Buffer;

int main(int argc, char *argv[] ){
	char sd_name[] = "/shm_SCOMP_ex7";
	Buffer *buff;
	int fd, sd_size = sizeof(Buffer);
	pid_t pid;

	if((fd = shm_open(sd_name, O_CREAT|O_EXCL|O_RDWR, S_IRUSR|S_IWUSR))<0){
		printf("Error on shm_open.\n");
	}
	if(ftruncate (fd, sd_size)!=0) {
		printf("Error on ftruncate.\n");
	}
	buff = (Buffer*) mmap(NULL,sd_size,PROT_READ|PROT_WRITE,MAP_SHARED,fd,0);
	buff->counter=0;

	if((pid=fork())<0){
		printf("Error on fork.\n");
	} else if (pid == 0) {
		// on Child
		// as Producer
		int i, in = 0; //pos de escrita
		for (i = 0; i < EXCHAN_VALUES; ++i) {
			while (buff->counter == BUFFER_SIZE);
			srand(time(NULL)+i);
			buff->values[in] = /*i+1;*/rand()%100;
			in = (in + 1) % BUFFER_SIZE;
			buff->counter++;
		}
		exit(0);
	}
	
	// on Parent
	// as Consumer
	int i, out = 0; //pos de leitura
	for (i = 0; i < EXCHAN_VALUES; ++i) {
		while (buff->counter == 0);
		printf("Value[%d] = %d\n", out, buff->values[out]);
		out = (out + 1) % BUFFER_SIZE;
		buff->counter--;
	}

	int status;
	wait(&status);

	int r = munmap(buff, sd_size); /* desfaz mapeamento */
	if (r < 0) exit(1);
	r = shm_unlink(sd_name); /* remove do sistema */
	if (r < 0) exit(1);

	return 0;
}
