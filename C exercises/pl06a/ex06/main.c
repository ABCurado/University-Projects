#include <stdio.h>
#include "str_cat.h"
char ptr1[] = "bibo para vever a bida !!";
char ptr2[] = "cjcp qbsb wfwfs b cjeb ..";
char ptr3[180] ;
int main(void) {
	printf("Frase1 = %s\n", ptr1);
	printf("Frase2 = %s\n", ptr2);
	str_cat();
	printf("Nova frase = %s\n", ptr3);
	return 0;
}
