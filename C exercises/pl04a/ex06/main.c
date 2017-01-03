#include <stdio.h>
#include "variaveis.h"
#include "aritmetica.h"

int op1=0, op2=0, res=0, resto=0;

int main(void) {
	char operacao;

	printf("Digite um operando seguido do operador e do 2º operando\n(Ex: 5 + 3):\n");
	scanf("%d %c %d",&op1,&operacao,&op2);

	switch(operacao) {
		case '+' :
			somar();
			break;
		case 'x' :
			multiplicar();
			break;
		case '*' :
			multiplicar();
			break;
		case 'X' :
			multiplicar();
			break;
		case '-' :
			subtrair();
			break;
		case '/' :
			dividir();
			break;
		default :
			printf("Operação inválida!\n");
	}
	printf("Resultado: %d", res);
	if (resto>0)
		printf(" resto %d", resto);
	printf("\n");
	return 0;
}
