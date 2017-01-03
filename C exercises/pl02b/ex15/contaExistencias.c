#include "funcoes.h"

int contaExistencias(int vec[],int size){	
		int i, ocorrencias = 0;
		for(i=0; i < size ; i++){ // corre os valores do vetor
			ocorrencias += compara(vec[i],vec[i+1],vec[i+2]);//invoca a função para verificar se ocorreu o pretendido		
		}   
		return ocorrencias; 	
}

