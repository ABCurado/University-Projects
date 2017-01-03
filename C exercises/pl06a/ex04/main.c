#include <stdio.h>
#include "encripta.h"
char frase[] = "bibo para vever a bida !! ";
char novaFrase[100];
int main(void) {
	int ocorrencias;
	printf("Frase = %s\n", frase);
	ocorrencias=encripta();
	printf("Nova frase = %s\n", frase);
	printf("ocorrencias = %d\n", ocorrencias);
	return 0;
}
