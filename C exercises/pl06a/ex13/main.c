#include <stdio.h>
#include "preenche_vec.h"

int * ptrvec;
int num = 10;

int main(void) {
	int i, nr;
	int vec[]={-1,2,-3,4,-5,6,-7,8,-9,10};

	printf("\nVetor: [");
	for (i=0;i<num;i++) {
		printf(" %d",vec[i]);
	}
	printf(" ]\n");

	ptrvec = vec;
	nr=preenche_vec();
	printf("\nNúmero de elementos alterados: %d.\n",nr);

	printf("\nVetor alterado: [");
        for (i=0;i<num;i++) {
                printf(" %d",vec[i]);
        }
        printf(" ]\n");

	return 0;
}
