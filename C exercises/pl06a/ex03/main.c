#include <stdio.h>
#include "str_copy_p2.h"

char * ptr1;
char * ptr2;

int main(void) {
	char str[] = "Bombeiros voluntários.";
	printf("'%s':\n",str);
	ptr1 = str;

	char str2[100] = "";
	ptr2 = str2;

	int cont=str_copy_p2();
	printf("Resultado:\n");
	printf("%s\n",ptr2);
	printf("%d chars alterados\n",cont);

	return 0;
}
