#include <stdio.h>
#include "vec_search.h"
short int ptrvec[12] = {88,6,5,5,667,26,62,61,62,5652,62};
short int num=12;
short int num_pretendido = 62;

void imprimir(short int ptrvec[]){
	int i = 0;  	
	for(i = 0 ; i < num; i++){
		printf(" %d",ptrvec[i]);	
	}
	printf("\n");
}
int main(void) {
	imprimir(ptrvec);
	short int * posicao;	
	posicao = vec_search();
	printf("O numero %d foi encontrado na posiçao %p inicialmente\n",num_pretendido,posicao);
	return 0;
}

