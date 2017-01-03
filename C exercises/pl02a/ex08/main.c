/* A solucao para o Exercicio anterior já utiliza aritmetica de apontadores! */

#include <stdio.h>
#include "ordenaVetor.h"

void imprime_vetor(int * ptr);

int main()
{
	int x[10]={10,324,3,21,1,5213,236,123,532,13};
	int * pointer;
	pointer = x;

	printf("Antes:\n");
	imprime_vetor(pointer);

	ordenaVetor(pointer,10);

	printf("Depois:\n");
	imprime_vetor(pointer);

	return 0;
}


void imprime_vetor(int * ptr) {
	printf("Vetor:");
        int i = 0;
        for( i = 0;i < 10 ; i++){
                printf("%d ", *ptr);
                ptr++;
        }
        printf("\n");
}
