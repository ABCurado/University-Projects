#include <stdio.h>
#include "quadrado.h"
int main(void) {
	int resultado,x;
	x = 25;
	printf("Numero: %d\n",x);
	resultado = quadrado(x);
	printf("Quadrado do numero = %d\n", resultado);
	return 0;
}
