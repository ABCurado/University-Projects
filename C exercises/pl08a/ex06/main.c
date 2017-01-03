#include <stdio.h>
#include "compare_string.h"

int main(void) {
	char * str1, * str2;
	char str_1[] = "O rato roeu a rolha da garrafa do rei da Rússia.";
	char str_2[] = "Três tristes tigres!";
	str1 = str_1;
	str2 = str_2;
	int res = testa_iguais(str1,str2);

	printf("\nTestando os seguintes strings:\n");
	printf("\n%s\n",str1);
	printf("\n%s\n",str2);
	printf("\nResultado: %d\n",res);

	char str_3[] = "Arquitectura de computadores.";
        char str_4[] = "Arquitectura de computadores.";
        str1 = str_3;
        str2 = str_4;
        int res2 = testa_iguais(str1,str2);

	printf("\nTestando os seguintes strings:\n");
        printf("\n%s\n",str1);
        printf("\n%s\n",str2);
        printf("\nResultado: %d\n",res2);

	return 0;
}
