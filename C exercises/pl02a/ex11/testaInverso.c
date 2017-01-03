#include <stdio.h>
#include "testaInverso.h"

int testaInverso(char* apontador){
	int fim,inicio=0;
	fim = concatena(apontador);
	paraMaiusculas(apontador);
	fim--; // pois o fim esta na posiçao 0 da sting
	while(*(apontador+inicio) != 0){ //Corre a string
		if(*(apontador+inicio) != *(apontador+fim)){ //Caso sejam diferente a funçao retorna 0
			return 0 ;
		}
        inicio++; //incrementa ao apontador que começao no inicio
		fim--;//decrementa ao que começa no fim
	}	
	return 1; //Caso nao tenha retornado falso retorna verdadeiro
}

