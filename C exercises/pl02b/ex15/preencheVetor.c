#include "funcoes.h"
#include <stdlib.h>

void preencheVetor(int vec[], int size){
        int i;
	for(i = 0 ; i <size ; i++){ //percorre o vetor vec
	    vec[i]= rand() % 21 ; // recorrendo á funçao rand() e recorrendo ao operador % para dizer o valor maximo, atribui um numero aleatorio entre 0 e 20
	}
}
