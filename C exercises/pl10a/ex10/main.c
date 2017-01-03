#include <stdio.h>
#include <stdlib.h>
#include "procura_matriz.h" 

void preenche_matriz(int **matrix,int numberOfLines,int numberColumns){
	int j = 0;
	for (int rows=0; rows < numberOfLines; rows++){
    	for(int columns=0; columns < numberColumns; columns++){
	         matrix[rows][columns] = j;
			 j++;		
		}
 	}
}

void imprime_matriz(int **matrix,int numberOfLines,int numberColumns){
	for (int rows=0; rows < numberOfLines; rows++){
    	for(int columns=0; columns < numberColumns; columns++){
         printf("%d     ", matrix[rows][columns]);
		}
  	  printf("\n");
 	}
}

int main(void) {
	int **mat = 0;
	int resultado ;
	mat = cria_matriz(4,5);
	preenche_matriz(mat,4,5);
	imprime_matriz(mat,4,5);
	printf("Procura o numero 15\n");
	resultado = procura_matriz(mat,15,4,5);
	printf("Resultado =%d\n",resultado);
	printf("Procura o numero 20\n");
	resultado = procura_matriz(mat,20,4,5);
	printf("Resultado =%d\n",resultado);
	free(mat[0]);
	free(mat);
	return 0;
}

