#include <time.h>
#include <stdio.h>
#include <stdlib.h>
#include "cria_matriz.h"
#include "imprime_matriz.h"
#include "conta_pares_matriz.h"

int main(void)
{
	int l,c,i,j;
	l=3;
	c=3;
	int ** mat = cria_matriz(l,c);

	srand(time(NULL));
	for (i=0;i<l;i++)
		for (j=0;j<c;j++)
			mat[i][j]=rand() % 10;

/*	int v[] = {1,2,3,4};
	int k=0;
	for (i=0;i<l;i++) {
		printf("Endereço da linha %d => %p; 1º apontado: %p\n",i,&mat[i],&mat[i][0]);
                for (j=0;j<c;j++) {
                        mat[i][j]=v[k];
			k++;
			printf("Elemento mat[%d][%d] = %d => %p\n",i,j,mat[i][j],&mat[i][j]);
		}
	}
*/
	printf("\n");
	imprime_matriz(mat,l,c);

	int pares = conta_pares_matriz(mat,l,c);
	printf("Pares encontrados: %d\n",pares);

	for (i=0;i<l;i++)
		free(mat[i]);
	free(mat);

	return 0;
}
