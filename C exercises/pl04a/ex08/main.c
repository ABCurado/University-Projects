#include <stdio.h>
#include "somatorio.h"

int n = 0;

int main(void) {

	printf("Digite um número n:\n");
	scanf("%d",&n);

	printf("Resultado: %d\n", somatorio());
	return 0;
}
