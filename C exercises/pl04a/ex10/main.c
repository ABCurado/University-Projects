#include <stdio.h>
#include "soma.h"

int op1=0,op2=0,res=0,CF=0,OF=0;

int main(void) {
	op1 = 1;
	op2 = -1;
	printf("Somando %d e %d:\n",op1,op2);
 	soma();
	printf("A soma é: %d Carry flag: %d Overflow flag: %d\n",res,CF,OF);

	op1 = 2147483647;
	op2 = 1;
	CF=0,OF=0;
	printf("Somando %d e %d:\n",op1,op2);
 	soma();
	printf("A soma  é: %d Carry flag: %d Overflow flag: %d\n",res,CF,OF);

	printf("Digite os dois numeros a somar:\n");
	scanf("%d %d",&op1,&op2);
	CF=0,OF=0;
 	soma();
	printf("A soma  é: %d Carry flag: %d Overflow flag: %d\n",res,CF,OF);
	return 0;
}

