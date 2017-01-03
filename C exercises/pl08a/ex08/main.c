#include <stdio.h>
#include "calcula.h"

int main(void) {

	int a=2,b=3;

	printf("Resultado: (%d*%d) - (%d+%d) = %d\n",a,b,a,b,calcula(a,b));

	return 0;
}
