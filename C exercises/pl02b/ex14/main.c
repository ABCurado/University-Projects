#include <stdio.h>
#include "frequencia.h"
#define NR_DE_NOTAS 10

int main() {

	int notas[NR_DE_NOTAS] = {10,20,15,13,12,15,20,10,12,14};
	int * vetNotas = notas;
	int nElem = NR_DE_NOTAS;

	int freq[(NR_DE_NOTAS*2)];
	int * vetFreq = freq;

	int nr_notas_distintas = frequencia(vetNotas, nElem, vetFreq);

	int * i;
	for (i = vetFreq; i < (vetFreq+nr_notas_distintas*2); i++) {
		printf("Nota: %d - Frequencia: %d\n", *i, *(i+1));
		i++;
	}

	return 0;

}
