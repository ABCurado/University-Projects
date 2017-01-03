#include <stdio.h>
#include "conta_bits_um.h"

int conta_bits_um_C(int x);

int main(void) {

	int a=33002;

	printf("Bits activos de %d em C: %d\n",a,conta_bits_um_C(a));

	printf("Bits activos de %d em Assembly: %d\n",a,conta_bits_um(a));

	a = -12;

	printf("Bits activos de %d em C: %d\n",a,conta_bits_um_C(a));

	printf("Bits activos de %d em Assembly: %d\n",a,conta_bits_um(a));

	return 0;
}

int conta_bits_um_C(int x) {
	int cont = 0;

	while(x!=0) {
		cont += (x % 2);
		x = (unsigned int)x >> 1;
	}

	return cont;
}
