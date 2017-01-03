#include <stdio.h>
#include "vec_corta.h"
long long int ptrvec[12] = {88,34254647234542452,534,2635345,667,26,6,4294967296,62,5652,6};
int num=12;


void imprimir(long long int ptrvec[]){
	int i = 0;  	
	for(i = 0 ; i < num; i++){
		printf(" %lld",ptrvec[i]);	
	}
	printf("\n");
}
int main(void) {
	imprimir(ptrvec);
	int ocorrencias;	
	ocorrencias = vec_corta();
	printf("Foram cortados %d numeros \n",ocorrencias);
	imprimir(ptrvec);
	return 0;
}

