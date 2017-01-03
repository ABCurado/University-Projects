#include <stdio.h>
#include "str_copy_p.h"

char * ptr1;
char * ptr2;

int main(void) {
	char str[] = "Era uma bez uma baca belha.";
	printf("'%s':\n",str);
	ptr1 = str;

	char str2[100] = "";
	ptr2 = str2;

	int count=str_copy_p();
	printf("Resultado:\n");
	printf("%s\n",ptr2);
	printf("%d chars alterados.\n",count);
	return 0;
}
