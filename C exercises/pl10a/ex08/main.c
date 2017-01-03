#include <stdio.h>
#include <stdlib.h>
#include "paraMaiusculas.h" 


int main(void) {
	char frase[] = "Tudo para maiusculas";
	printf("Frase = %s\n",frase);
	char novaFrase[50];
	char * p;
	p = novaFrase;
	p = paraMaiusculas(frase);
	printf("Nova frase = %s\n",p);
	free(p);
	return 0;
}

