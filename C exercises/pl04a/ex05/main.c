#include <stdio.h>
#include "asm.h"

int larg=0, comp=0, res=0;
void get_area();

int main(void) {

	printf("######Área de um retangulo#######\n");
	printf("Largura:");
	scanf("%d",&larg);
	printf("Comprimento:");
	scanf("%d",&comp);

	get_area();

	printf("Área: %d\n", res);
	return 0;
}

void get_area() {
	int i;
	for (i=comp; i>0; i--) {
		soma();
	}
}
