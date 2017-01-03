#include <stdio.h>

void imprime_matriz(int **mat, int l, int c)
{
	int i, j;
	for (i=0;i<l;i++) {
		for (j=0;j<c;j++)
			printf("\t%d",mat[i][j]);
		printf("\n");
	}
}
