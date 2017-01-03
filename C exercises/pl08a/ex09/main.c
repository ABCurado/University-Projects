#include <stdio.h>
#include "calc.h"
int main(void) {
	int resultado,z,b,c;
	int * a;
	z = 5;
	a = &z;
	b=5;
	c=5;
	printf("*a = %d, b = %d, c= %d\n",*a,b,c);
	resultado =  calc(a,b,c);
	printf("Resultado %d\n",resultado);
	z = 0;	
	b=3;
	c=1;
	printf("*a = %d, b = %d, c= %d\n",*a,b,c);
	resultado =  calc(a,b,c);
	printf("Resultado %d\n",resultado);
	z = 10;	
	b=1;
	c=15;
	printf("*a = %d, b = %d, c= %d\n",*a,b,c);
	resultado =  calc(a,b,c);
	printf("Resultado %d\n",resultado);
	return 0;
}
