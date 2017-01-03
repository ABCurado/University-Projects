#include <stdio.h>
#include "converterToLow.h"

int main() {

	char str[100];
	char * ap = str;
	printf("Digite um string para conversão em lower case:\n");
	fgets(str, sizeof(str), stdin);
	converteToLow(ap);
	printf("%s\n", str);
	return 0;

}
