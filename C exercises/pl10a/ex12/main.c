#include <stdio.h>
#include <stdlib.h>
#include "soma_matrizes.h" 

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
	int **a = 0,**b = 0,**resultado = 0;
	a = cria_matriz(4,5);
	b = cria_matriz(4,5);
	resultado = cria_matriz(4,5);
	preenche_matriz(a,4,5);
	preenche_matriz(b,4,5);
  	printf("A = \n");
	imprime_matriz(a,4,5);
  	printf("B = \n");
	imprime_matriz(b,4,5);
	printf("Soma Matrizes \n");
	soma_matrizes(a,b,resultado,4,5);
	imprime_matriz(resultado,4,5);
	free(a[0]);
	free(a);
	free(b[0]);
	free(b);
	free(resultado[0]);
	free(resultado);
	return 0;
}

