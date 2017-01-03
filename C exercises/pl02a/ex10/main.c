#include <stdio.h>
#include "somaPares.h"

void imprime_vetor(int * ptr);

int main()
{
	int x[30] = {8,1,2,3,4,5,6,7,8}, soma = 0;
	int * pointer;
	pointer = x;

	imprime_vetor(pointer);
	soma = somarPares(pointer);

	printf("Resultado da soma dos elementos pares: %d\n", soma);
	return 0;
}

/* Imprime vetor */

void imprime_vetor(int * ptr) {
	printf("Vetor:");
        int i = 0, tam = *ptr;
        for(i = 0; i < tam+1; i++){
                printf("%d ", *ptr);
                ptr++;
        }
        printf("\n");
}
