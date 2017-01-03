#include <stdio.h>
#include "space_count.h"

char * ptr1;
int count=0;

int main(void) {
	char str[] = "String de teste nr 1.";
	printf("'%s':\n",str);
	ptr1 = str;
	count = space_count();
	printf("%d espaços encontrados.\n",count);
	return 0;
}
