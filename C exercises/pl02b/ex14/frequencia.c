#include "ordenaVetor.h"

int frequencia(int * vetNotas, int nElem, int * vetFreq) {
	int nr_notas_distintas = 0, * lim = vetNotas+nElem;
	ordenaVetor(vetNotas, nElem);

	/* Depois de ordenado o vetor, enquanto os elementos subsequentes forem iguais ao elemento em estudo
	 * incrementa-se a correspondente frequencia, senao, escreve-se os dados reunidos no vetor de resultados
	 * e avanca-se para o elemento seguinte
	*/

	while (vetNotas < lim) {
		int fr = 1;
		while (*(vetNotas+fr) == *vetNotas) {
			fr++;
		}
		/*Escrita no vetor dos resultados*/
		*vetFreq = *vetNotas;
		*++vetFreq = fr;

		/* Avanco para o proximo elemento a estudar e atualizacao de variaveis auxiliares*/
		vetFreq++;
		vetNotas += fr;
		nr_notas_distintas++;
	}
	return nr_notas_distintas;
}
