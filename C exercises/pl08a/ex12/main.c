#include <stdio.h>
#include "activa_bit.h"

int activa_bit_C(int *n, int pos);

int main(void) {
	int * n;
	int * n2;
	int i;
	int j=0;
	for (i=10; i<=40; i+=5) {
		int a=i;
		int b=i;
		j++;
		n = &a;
		n2 = &b;
		int resa = activa_bit_C(n,j);
		int resb = activa_bit(n2,j);

		printf("\n(C) Numero %d com bit pos %d activado: %d (%d)\n",i,j,*n,resa);
		printf("(Assembly) Numero %d com bit pos %d activado: %d (%d)\n",i,j,*n,resb);

	}
	return 0;
}

int activa_bit_C(int *n, int pos)
{
	if (*n & (1<<pos))
		return 0;
	*n |= (1<<pos);
	return 1;
}

/* Alternativa
int activa_bit_C(int *n, int pos) {
	int nr = *n;
	*n |= 1 << pos;
	nr &= 1 << pos;
	nr >>= pos;
	return nr ^= 1;
}
*/
