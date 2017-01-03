#include <stdlib.h>
#include <stdio.h>

int **cria_matriz(int y, int k)
{
	int i;
	int **matriz; // endereço da matriz
	matriz = (int**) calloc(y,sizeof(int*));
	if(matriz == NULL) {
		printf("Erro na reserva de memoria\n");
		exit(1);
	}

	for (i = 0; i < y ; i++) {

		/* reservar um vetor de apontadores de inteiros representando as colunas para cada linha */
		*(matriz+i) = (int*) calloc(k,sizeof(int));

		if (matriz[i] == NULL) {
			printf("Erro na reserva de memoria\n");
			exit(1);
		}
	}

	return matriz;
}
