#include <stdio.h>
#include <stdlib.h>
#include "cria_matriz.h"
#include "imprime_matriz.h"

int main(void)
{
	int ** mat = cria_matriz(5,5);
	imprime_matriz(mat,5,5);

	int i,j;
	for (i=0;i<5;i++)
		for (j=0;j<5;j++)
			mat[i][j]=rand() % 10;

	printf("\n");
	imprime_matriz(mat,5,5);

	for (i=0;i<5;i++)
		free(mat[i]);
	free(mat);

	return 0;
}
