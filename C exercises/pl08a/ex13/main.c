#include <stdio.h>
#include "activa_esquerda_direita.h"
#include <stdlib.h>
#include <limits.h>
/*Codigo conseguido na pagina http://stackoverflow.com/questions/1024389/print-an-int-in-binary-representation-using-c com algumas alteraçoes feitas*/
void int2bin(int i)
{
    size_t bits = sizeof(int) * CHAR_BIT;

    char str[32];

    // type punning because signed shift is implementation-defined
    unsigned u = *(unsigned *)&i;
    for(; bits--; u >>= 1)
    	str[bits] = u & 1 ? '1' : '0';

	printf("%s\n",str);
}
int main(void) {
	int resultado,a;
	a=0;
	resultado =  activa_esquerda_direita_em_C(a,2,4);
	printf("Numero em C - %d foi mantido igual entre o bit 2 e 4= %d\n",a,resultado);
	int2bin(resultado);
	resultado =  activa_esquerda_direita(a,2,4);
	printf("Numero em Assembly - %d foi mantido igual entre o bit 2 e 4= %d\n",a,resultado);
	int2bin(resultado);

	return 0;
}

