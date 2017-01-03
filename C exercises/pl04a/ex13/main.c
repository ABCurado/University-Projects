#include <stdio.h>
#include "operacoes.h"

int  A=0,B=0,C=0,D=0;
long long res = 0;

int main(void) {

	A = 1;
	B = 10;
	C = 100;
	D = 1000;
	printf("A = %d B = %d C = %d D = %d:\n",A,B,C,D);
 	operacoes();
	printf("O resultado é é: %lld \n",res);	

	A = 5;
	B = 1005;
	C = 100005;
	D = 1000005;
	printf("A = %d B = %d C = %d D = %d:\n",A,B,C,D);
 	operacoes();
	printf("O resultado é é: %lld \n",res);	

	
	A = 100;
	B = 10005;
	C = 100005;
	D = 10000005;
	printf("A = %d B = %d C = %d D = %d:\n",A,B,C,D);
 	operacoes();
	printf("O resultado é é: %lld \n",res);	
	return 0;
}

