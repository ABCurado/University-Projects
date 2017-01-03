#include <stdlib.h>
#include "soma_matrizes.h"

int **cria_matriz(int rows, int columns){
	int i;
	int **mat = (int **) calloc(rows, sizeof(int*));
	mat[0] = (int *) calloc(rows * columns, sizeof(int));
	for(i=1;i < rows;i++){
		mat[i] = mat[0] + i * columns;
	}
	return mat;
}


