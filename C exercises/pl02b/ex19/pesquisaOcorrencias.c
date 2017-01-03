#include "pesquisaOcorrencias.h" 
#include <stdio.h>
int pesquisa_ocorrencias_palavra(char*  str,char* palavra,int* indices_palavras){
	int ocorrencias= 0,indice=0 ;
	while(indice != 100 ){
		printf("Entrou com o indice %i \n",indice);
		indice = pesquisa_palavra(str,palavra,indice);
		if(indice == -1){ return ocorrencias;}
		*indices_palavras = indice;
		indices_palavras++;
		ocorrencias++;
		indice++;
	}
	return ocorrencias;
}	
