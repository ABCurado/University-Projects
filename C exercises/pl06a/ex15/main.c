#include <stdio.h>
#include "comuns.h"
long int ptrvec1[14] = {1,5,45,423,234,3,4,2,4,453,10,666,1,0};
long int ptrvec2[14] = {1,8,45,423,234,83,4,88,4,89,10,88,1,3};
int ptrvec3[14] = {};
int num=14;


void imprimirShort(long int ptrvec[]){
	int i = 0;  	
	for(i = 0 ; i < num; i++){
		printf("  %ld",ptrvec[i]);	
	}
	printf("\n");
}

void imprimir( int ptrvec[], int ocorrencias){
	int i = 0;  	
	for(i = 0 ; i < ocorrencias; i++){
		printf("  %d",ptrvec[i]);	
	}
	printf("\n");
}

int main(void) {
	imprimirShort(ptrvec1);
	imprimirShort(ptrvec2);
	int ocorrencias;	
	ocorrencias = comuns();
	imprimir(ptrvec3,ocorrencias);
	printf("Foram encontrados %d numeros \n",ocorrencias);

	return 0;
}

