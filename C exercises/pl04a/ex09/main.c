#include <stdio.h>
#include "jogo.h"

int x = 0;

int main(void) {

	printf("Digite um número:\n");
	scanf("%d",&x);

	printf("Resultado: %d\n", jogo());
	return 0;
}
