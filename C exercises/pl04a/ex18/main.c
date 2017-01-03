#include <stdio.h>
#include "testes.h"

int x=0;
int res=0;

void show() {
	printf(" (%d)	Resultado: %d",x,res);
	switch (res) {
                case 1:
                        printf(" <- par e positivo ;)\n");
                        break;
                case 2:
                        printf(" <- par e negativo ;)\n");
                        break;
                case 3:
                        printf(" <- ímpar e positivo ;)\n");
                        break;
                case 4:
                        printf(" <- ímpar e negativo ;)\n");
                        break;
                default:
                        printf("ERRO!\n");
	}
}

int main(void) {

	x=2;
	testes();
	show();

	x=-2;
        testes();
        show();

	x=3;
        testes();
        show();

	x=-3;
        testes();
        show();

	printf("\nDigite um número:\n");
	scanf("%d",&x);

	testes();
	show();
	return 0;
}
