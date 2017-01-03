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

#define REPEATS 100000
#define STR_SIZE 50
#define NR_DISC 10

struct aluno {
	int numero;
	char nome[STR_SIZE];
	int disciplinas[NR_DISC];
};

int main(int argc, char *argv[] ){
	char sd_name[] = "/shm_SCOMP_ex7";
	struct aluno *aluno;
	int fd, sd_size = sizeof(struct aluno);
	pid_t pid;

	if((fd = shm_open(sd_name, O_CREAT|O_EXCL|O_RDWR, S_IRUSR|S_IWUSR))<0){
		printf("Error on shm_open.\n");
	}
	if(ftruncate (fd, sd_size)!=0) {
		printf("Error on ftruncate.\n");
	}
	aluno = (struct aluno*) mmap(NULL,sd_size,PROT_READ|PROT_WRITE,MAP_SHARED,fd,0);

	if((pid=fork())<0){
		printf("Error on fork.\n");
	} else if (pid == 0) {
		// on Child
		while(aluno->numero==0) {
		}
		int i, max=aluno->disciplinas[0], min=aluno->disciplinas[0];
		float sum=aluno->disciplinas[0];
		for (i = 1; i < NR_DISC; ++i) {
			if(aluno->disciplinas[i] > max) {
				max = aluno->disciplinas[i];
			}
			if(aluno->disciplinas[i] < min) {
				min = aluno->disciplinas[i];
			}
			sum = sum + aluno->disciplinas[i];
		}

		printf("\nNúmero: %d\n", aluno->numero);
		printf("Nome: %s\n", aluno->nome);
		printf("Maior nota: %d\n", max);
		printf("Menor nota: %d\n", min);
		printf("Nota média: %.2f\n\n", sum/NR_DISC);
		exit(0);
	}
	
	int max = 20, min=0, i;
	for (i = 0; i < NR_DISC; ++i) {
		srand(time(NULL)+i);
        int random = rand() % max + min; // generate number between 1 and 5
        aluno->disciplinas[i] = random;
	}
	strcpy(aluno->nome, "Quim Bala");
	aluno->numero = 12345;

	int status;
	wait(&status);

	int r = munmap(aluno, sd_size); /* desfaz mapeamento */
	if (r < 0) exit(1);
	r = shm_unlink(sd_name); /* remove do sistema */
	if (r < 0) exit(1);

	return 0;
}
