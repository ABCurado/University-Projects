#include <stdio.h>
#include "vec_pos.h"

long int * ptrvec;
int num = 10;

int main(void) {
	int i, nr;
	long int vec2[]={11,2,13,4,15,6,17,8,19,10};

	printf("Vetor: [");
	for (i=0;i<num;i++) {
		printf(" %li",vec2[i]);
	}
	printf(" ]\n");

	ptrvec = vec2;
	nr=vec_pos();
	printf("Número de elementos maiores ou iguais a 10: %d\n.",nr);

	return 0;
}
