#include <stdio.h>
#include "rotate.h"
#include <stdlib.h>
#include <limits.h>
/*Codigo conseguido na pagina http://stackoverflow.com/questions/1024389/print-an-int-in-binary-representation-using-c com algumas alteraçoes feitas*/
void int2bin(int i)
{
    size_t bits = sizeof(int) * CHAR_BIT;

    char * str = malloc(bits + 1);

    // type punning because signed shift is implementation-defined
    unsigned u = *(unsigned *)&i;
    for(; bits--; u >>= 1)
    	str[bits] = u & 1 ? '1' : '0';

	printf("%s\n",str);
}
int main(void) {
	int resultado,x;
	x=127;
	printf("x= %d\n",x);
	int2bin(x);
	resultado =  rotate_right(x,1);
	printf("Rotate right 1 -%d\n",resultado);
	int2bin(resultado);
	resultado =  rotate_left(x,1);
	printf("Rotate left 1-%d\n",resultado);
	int2bin(resultado);
	x=1;
	printf("x= %d\n",x);
	resultado =  rotate_left(x,4);
	printf("Rotate left 4 -%d\n",resultado);
	int2bin(resultado);
	resultado =  rotate_right(x,2);
	printf("Rotate right 2 -%d\n",resultado);
	int2bin(resultado);
	return 0;
}

