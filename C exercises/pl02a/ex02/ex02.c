#include <stdio.h>
#include "ler_escrever.h"
#include "conversao.h"
#define VEC_SIZE 4

int main() {
	printf("Escrita em vetor de inteiros (Ex.2)\n");
	int vec[VEC_SIZE];
	int * ap;
	ap = vec;
	ler(ap,VEC_SIZE);
	escrever(ap,VEC_SIZE);
	ap = vec;
	return 0;
}
