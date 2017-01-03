#include <stdio.h>
#include "soma_e_maior.h"

int main(void) {
	int nr1 = 100;
	int nr2 = 234;
	int * maior;
	int aux = 0;
	maior = &aux;
	int soma = soma_e_maior(nr1,nr2,maior);
	printf("Soma de %d e %d = %d, Maior = %d\n",nr1,nr2,soma,aux);
	return 0;
}
