#include <stdio.h>
#include "potencia.h"

int main() {

	char str_base[15], str_exp[3];
	int base = 0, y = 0;
	int * x;
	x = &base;

	printf("Digite uma base:\n");
	scanf("%s", str_base);
	base = toInt(str_base);

	printf("Digite um expoente:\n");
	scanf("%s", str_exp);
	y = toInt(str_exp);

	potencia_por_referencia(x, y);
	printf("Resultado :%d\n", *x);
	return 0;
}

/* A utilizacao de fgets() para obtencao de inteiros nao funcionou,
 * algo acontece com a ultima posicao do vetor de char (aparentemente
 * um caracter de newline)
*/
