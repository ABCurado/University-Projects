#include <stdio.h>
#define LEN 10

int string_para_inteiro(char string[]) {
	int aux=0, i=0;
	while (string[i] != 0) {
		aux*=10;
		aux+= (int)string[i] - '0';
		i++;
	}
	return aux;
}

int main() {
	printf("Digite um numero:\n");
	char str[LEN] = "";
	scanf("%s" , str);
	int nr = string_para_inteiro(str);
	printf("Número resultante : %d\n", nr);
	return 0;
}

