#include "funcoes.h"

int preencheEConta(int vec[],int size){
	preencheVetor(vec,size);//preenche o vetor
	int i;
	for(i = 0; i < size ; i++){
		printf("%i ",vec[i]);
	}			
	return contaExistencias(vec,size); //invoca a função para contar o numero de ocorrencias
}
