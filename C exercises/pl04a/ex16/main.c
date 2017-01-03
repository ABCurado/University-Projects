#include <stdio.h>
#include "soma.h"
#include "subtraccao.h"
#include "multiplicacao.h"
#include "divisao.h"

int op1=0;
int op2=0;
int res=0;
int resto=0;

int main(void) {
	char operacao;

	printf("Digite um operando seguido do operador e do 2º operando\n(Ex: 5 + 3):\n");
	scanf("%d %c %d",&op1,&operacao,&op2);

	switch(operacao) {
		case '+' :
			soma();
			break;
		case 'x' :
			multiplicacao();
			break;
		case '*' :
			multiplicacao();
			break;
		case 'X' :
			multiplicacao();
			break;
		case '-' :
			subtraccao();
			break;
		case '/' :
			divisao();
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
