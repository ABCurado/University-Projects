#include <stdlib.h>
#include <stdio.h>
#include "aluno.h"
#include "insere_notas.h"
#include "maiores.h"

int main(void)
{

	Aluno aluno1;
	Aluno * ptr_aluno;
	ptr_aluno=&aluno1;

	insere_dados(ptr_aluno,1140780,"Ruben","Rua das Pedras",29);

	int notas[] = {10,11,12,13,14,15,16,17,18,19};
	insere_notas(ptr_aluno,notas);
	printf("\nNotas:\n");
	imprime_dados(ptr_aluno);

	int * ptr_maiores;
	ptr_maiores = malloc(sizeof(int));

	int **ptr_ptr_maiores;
	ptr_ptr_maiores = &ptr_maiores;

	int minima = 14;

	int nrMaiores = procura_maiores(ptr_aluno, minima, ptr_ptr_maiores);

	ptr_maiores = *ptr_ptr_maiores;

	printf("\nNr de maiores que %d = %d, sendo:\n", minima, nrMaiores);

	int j;
	for (j=0;j<nrMaiores;j++)
		printf("Nota %d:\t%d;\n",j+1,*(ptr_maiores+j));

	free(ptr_maiores);

	return 0;
}
