#include <stdio.h>
#include "menor_data.h"
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
	int resultado,a,b;
	a= 2015 | 11 << 24 | 18<<16;
	b= 2015 | 11 << 24 | 16<<16;
	printf("a= 18-11-2015 a = %d\n",a);
	int2bin(a);
	printf("b= 16-11-2015 b = %d\n",b);
	int2bin(b);
	resultado =  menor_data_em_C(a,b);
	printf("Resultado em C = %d\n",resultado);	
	int2bin(resultado);
	resultado =  menor_data(a,b);
	printf("Resultado em Assembly = %d\n",resultado);	
	int2bin(resultado);
	return 0;
}

