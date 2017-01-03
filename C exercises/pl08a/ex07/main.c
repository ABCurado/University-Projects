#include <stdio.h>
#include "conta_pares.h"
void imprimir(int * ptrvec, int num){
	int i = 0;  	
	for(i = 0 ; i < num; i++){
		printf(" %d",*(ptrvec+i));	
	}
	printf("\n");
}
int main(void) {
	int resultado,num;
	int a[6] = {4,4,4,4,4,5};
	int * ap;
	ap = a;
	num = 6;
	printf("Vetor 1:");
	imprimir(ap,num);
	resultado =  conta_pares(a,num);
	printf("Resultado %d\n",resultado);
	int b[10] = {3,3,3,5,5,7,2,2,2,2};
	num = 10;
	ap = b;
	printf("Vetor 2:");
	imprimir(ap,num);
	resultado =  conta_pares(a,num);
	printf("Resultado %d\n",resultado);
	int c[4] = {3,3,3,5};
	num = 4;
	ap = c;
	printf("Vetor 2:");
	imprimir(ap,num);
	resultado =  conta_pares(a,num);
	printf("Resultado %d\n",resultado);
	return 0;
}
