#include <stdio.h>
#include "altera.h"

void altera_C(int *vec, int size);
void imprimeVetor(int *vec, int size);

int main(void) {
	int v[] = {5000,5500,6000,62345,12413414,12414,7000,8000,9000,10000};
	int size = 10;

	printf("\nAltera em C\n");
	printf("Vetor antes:\n");
	imprimeVetor(v,size);
	altera_C(v,size);
	printf("Vetor depois:\n");
	imprimeVetor(v,size);

	int v1[] = {5000,5500,6000,62345,12413414,12414,7000,8000,9000,10000};
	printf("\nAltera em Assembly\n");
	printf("Vetor antes:\n");
	imprimeVetor(v1,size);
	altera(v1,size);
	printf("Vetor depois:\n");
	imprimeVetor(v1,size);

	return 0;
}

void altera_C(int *vec, int size)
{
	int mask = 0xF << 8;			// mask de extraccao
	int maskClear = ~mask;			// mask para desactivar bits caso seja necessario alterar
	int * i;

	for (i=vec ; i < vec+size ; i++) {
		int bits = (*i & mask) >> 8;	//extraccao dos 4 bits - sign do 2º byte (pos 8-15)
		if (bits > 3) {
			bits--;		  // 4 bits - 1 un
			bits <<= 8;
			*i &= maskClear;  // desactivar os 4 bits do num
			*i |= bits;	  // colocar os novos 4 bits no num
		}
	}
}

void imprimeVetor(int *vec, int size)
{
	printf("[ ");
	int * i;
	for (i=vec; i < (vec+size); i++) {
		printf("%d ",*i);
	}
	printf("]\n");

}
