#include <stdlib.h>
#include "aluno.h"

int procura_maiores(Aluno *a, int minima, int **ptr_ptr_maiores) {
	int nr_maiores = 0;
	int i;
	for (i=0;i<10;i++) {

		if (a->notas[i] > minima) {

			int * temp = NULL;
		        temp = (int *) realloc(*ptr_ptr_maiores, (nr_maiores+1) * sizeof(int));

		        if (temp != NULL) {
				*ptr_ptr_maiores = temp;
				*(temp+nr_maiores) = a->notas[i];
				nr_maiores++;
			}
		}
	}
	return nr_maiores;
}
