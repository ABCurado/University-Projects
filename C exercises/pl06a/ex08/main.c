#include <stdio.h>
#include "vec_inc.h"
int ptrvec[12] = {88,6,5,5,666,26,62,62,62,5652,62};
int num=12;

void imprimir(int ptrvec[]){
	int i = 0;  	
	for(i = 0 ; i < num; i++){
		printf(" %d",ptrvec[i]);	
	}
	printf("\n");
}
int main(void) {
	imprimir(ptrvec);
	vec_inc();
	imprimir(ptrvec);

	return 0;
}

