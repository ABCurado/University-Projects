#include <stdio.h>
#include "capitalizar.h"

int main() {

	char str[100] = "o numero deve ser guardado";
	char str2[100] = "O maximo valor desse CICLO";
	char * ap = str;
	char * ap2 = str2;
	capitalizar(ap);
	capitalizar(ap2);
	printf("%s\n", ap);
	printf("%s\n", ap2);
	return 0;

}
