#include <stdio.h>
#include "somatorio.h"

int main(void) {
	int n;

	for (n = 1; n<=5; n++)
		printf("n = %d, somatorio(n) = %d\n",n,somatorio(n));
	return 0;
}
