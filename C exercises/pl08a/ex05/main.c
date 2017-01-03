#include <stdio.h>
#include "incrementa_e_quadrado.h"
int main(void) {
	int resultado,b,x;
	int * a ;
	a = &x;
    *a=7;
	b=25;
	printf("Numero: %d Apontado por b:%d\n",*a,b);
	resultado =  incrementa_e_quadrado(a,b);
	printf("Numero: %d Apontado por b:%d\n",*a,resultado); 
	b=10;
	*a=20;
	printf("Numero: %d Apontado por b:%d\n",*a,b);
	resultado =  incrementa_e_quadrado(a,b);
	printf("Numero: %d Apontado por b:%d\n",*a,resultado); 

	return 0;
}
