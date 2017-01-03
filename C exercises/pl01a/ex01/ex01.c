#include <stdio.h>

int main() {
	char c;
	int i;
	long li;
	float f;
	double d;
	
	printf("Tamanho de um char : %d bits\n", sizeof(c));
	printf("Tamanho de um int : %d bits\n", sizeof(i));
	printf("Tamanho de um long int : %d bits\n", sizeof(li));
	printf("Tamanho de um float : %d bits\n", sizeof(f));
	printf("Tamanho de um double : %d bits\n", sizeof(d));
	return 0;
}