#include "funcoes.h"
#include <stdio.h>
int organizaVetor(float x[],float y[],int size) {
  int i=0, j=0,tamanho=0;
  for(i=0;i<size;i++){ // corre o vetor a limpar de repetidos
	  for(j=0;j<tamanho;j++){ //corre o vetor livre de repetidos
		  if(x[i]==y[j]){ // caso encontre uma repetiçao nao corre mais o vetor livre de repetidos
			  break;
		  }
	   }
	   if(j==tamanho){ // Caso tenha percorrido o vetor  todo sem repetidos e nao encontrado adiciona o numero ao vetor e incrementa um ao seu tamanho
		   y[tamanho]=x[i];
		   tamanho++;
	   }
   }
   ordenaVetor(y,tamanho);
   return tamanho;
}

