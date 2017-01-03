#include <stdio.h>
#include "soma_multiplos_x.h"
void imprimir(char ptrvec[],int num){
	int i = 0;
	for(i = 0 ; i <  num; i++){
		printf("%d ",ptrvec[i]);
	}
	printf("\n");
}
int main(void) {
	int resultado,x = 65536;
	char vetor1[10] = {2,2,4,5,6,1,3,5,7,0};
	printf("Vetor Original= \n");
	imprimir(vetor1,10);
	resultado=soma_multiplos_x_em_C(vetor1,x);
	printf("Resultado em C= %d\n",resultado);
	soma_multiplos_x(vetor1,x);
	printf("Resultado em Assembly= %d\n",resultado);
	return 0;
}
