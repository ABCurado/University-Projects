#include "pesquisaOcorrencias.h" 
#include <stdio.h>

int pesquisa_palavra(char* str,char* palavra,int indice_atual){
	int flag,indice;
	while(*(str+indice_atual) != 0){
		flag = 1;
		indice = 0;
		while(*(palavra+indice) != 0 && flag==1 ){ //ciclo para comparar a palavra pretendida com o indice atual da string
			if(*(palavra+indice) != *(str+indice_atual+indice)){ flag=0; }// caso sejam diferentes a flag fica a 0 e acaba o ciclo
			indice++;// numero para iterar pela palavra 
		}
		if(flag==1){return indice_atual;}//caso a flag se mantenha a 1 a palavra foi encontrada e retorna o seu valor

		indice_atual++;
	}
	return -1;
}
