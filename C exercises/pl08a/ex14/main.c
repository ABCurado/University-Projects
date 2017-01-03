#include <stdio.h>
#include "junta_bits.h"
#include "intBin.h"

int junta_bits_C(int a, int b, int pos);

int main(void) {
	int a,b,pos,resC,resAS;

	a=37757147;
	b=255;
	pos = 16;

	printf("(C) A = %d ; B = %d ; pos = %d\n",a,b,pos);

	printf("\nA: ");
	intBin(a);

	printf("B: ");
	intBin(b);

	printf("____________________________________________\n = ");

	resC=junta_bits_C(a,b,pos);
	intBin(resC);
	printf("Resultado = %d\n",resC);

	printf("\n\n(AS) A = %d ; B = %d ; pos = %d\n",a,b,pos);

	resAS=junta_bits(a,b,pos);
	printf("\nResultado em AS: %d\n\n",resAS);

	return 0;
}

int junta_bits_C(int a, int b, int pos)
{
	int amask = -1 << pos;
	int bmask = ~amask;
	return (a & amask) | (b & bmask);
}
