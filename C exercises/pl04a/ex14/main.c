#include <stdio.h>
#include "operacoes.h"

int  A=0,B=0,n=0,i=1;
int res = 0;

int main(void) {
	A = 1;
	B = 1;
	n = 6;
	printf("A = %d B = %d n = %d :\n",A,B,n);
 	operacoes();
	printf("O resultado é é: %d \n",res);	

	A = 1;
	B = 1;
	n = 10;
	printf("A = %d B = %d n = %d :\n",A,B,n);
 	operacoes();
	printf("O resultado é é: %d \n",res);	

	
	A = 1;
	B = 1;
	n = 2;
	printf("A = %d B = %d n = %d :\n",A,B,n);
 	operacoes();
	printf("O resultado é é: %d \n",res);	
	return 0;
}

