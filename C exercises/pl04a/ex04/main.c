#include <stdio.h>
#include "asm.h"

int op1=0, op2=0, res=0;

int main(void) {
	printf("Valor op1:");
	scanf("%d",&op1);
	printf("Valor op2:");
	scanf("%d",&op2);
	soma();
	printf("(%d - 10) - (10 - %d) = %d:0x%x\n", op1,op2,res,res);
	return 0;
}

