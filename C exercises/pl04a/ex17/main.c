#include <stdio.h>
#include "multiplo.h"

int op1=0,op2=0,res=0;

int main(void) {
	op1 = 10;
	op2 = 1;
	printf("Op1 = %d, Op2 = %d :\n",op1,op2);
 	multiplo();
	if(res == 0){printf("É multiplo\n");}
	else{printf("Não é multiplo\n");}	

	op1 = 300;
	op2 = 10;
	printf("Op1 = %d, Op2 = %d :\n",op1,op2);
 	multiplo();
	if(res == 0){printf("É multiplo\n");}
	else{printf("Não é multiplo\n");}

	op1 = 303;
	op2 = 10;
	printf("Op1 = %d, Op2 = %d :\n",op1,op2);
 	multiplo();
	if(res == 0){printf("É multiplo\n");}
	else{printf("Não é multiplo\n");}			
	return 0;
}

