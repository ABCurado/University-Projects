#include <stdio.h>
#include "somabyte2.h"
void imprimir(int ptrvec[],int num){
	int i = 0;  	
	for(i = 0 ; i <  num; i++){
		printf(" %d",ptrvec[i]);	
	}
	printf("\n");
}
int main(void) {
	int x = 1;
	int vetor1[10] = {9,1,256,4352,65536,2097152,0,0,0,0};
	int vetor2[10];
	int vetor3[10];
	printf("Vetor Original= \n");
	imprimir(vetor1,10);
	somabyte2_em_C(x,vetor1,vetor2);
	printf("Vetor apos a opreação em C= \n");
	imprimir(vetor2,10);
	printf("Vetor Original= \n");
	imprimir(vetor1,10);	
	somabyte2(x,vetor1,vetor3);
	printf("Vetor apos a opreação em Assembly= \n");
	imprimir(vetor3,10);
	return 0;
}

