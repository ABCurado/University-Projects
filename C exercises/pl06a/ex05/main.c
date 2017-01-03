#include <stdio.h>
#include "decifra.h"

char * ptr1;
int count=0;

int main(void) {
	char str[] = "Tusjoh ef uftuf os 2/";
	printf("'%s':\n",str);
	ptr1 = str;
	count = decifra();
	printf("String decifrada:\n%s\n",ptr1);
	printf("%d caracteres descodificados.\n",count);
	return 0;
}
