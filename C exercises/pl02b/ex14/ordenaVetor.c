#include "ordenaVetor.h"

void ordenaVetor(int* pointer,int size) {
	int *i, *j, swap;
	for(i = pointer; i < (pointer + size); i++){
		for(j = i + 1; j < (pointer + size); j++){
			if(*j < *i){
				swap = *i;
				*i = *j;
				*j = swap;
			}
		}
	}
}

