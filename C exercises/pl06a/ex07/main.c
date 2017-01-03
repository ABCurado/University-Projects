#include <stdio.h>
#include "vec_sum.h"

int * ptrvec;
int num = 9;

int main(void) {
	int i;
	int vec[]={1,2,3,4,5,6,7,8,9};

	printf("Vetor: [");
	for (i=0;i<9;i++) {
		printf(" %d",vec[i]);
	}
	printf(" ]\n");

	ptrvec = vec;
	int sum=vec_sum();
	printf("Resultado da soma do vetor: %d\n.",sum);
	return 0;
}
