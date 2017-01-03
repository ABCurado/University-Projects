#include <stdio.h>
#include "soma.h"

int op1=0,op2=0;
long long res = 0;

int main(void) {
	op1 = 400000000;
	op2 = 429000000;
	printf("Somando %d e %d:\n",op1,op2);
 	soma();
	printf("A soma é: %lld \n",res);	

	op1 = 2147483647;
	op2 = 1;
	printf("Somando %d e %d:\n",op1,op2);
  	soma();
	printf("A soma é: %lld \n",res);


	printf("Digite os dois numeros a somar:\n");
	scanf("%d %d",&op1,&op2);
  	soma();
	printf("A soma é: %lld \n",res);
	return 0;
}

