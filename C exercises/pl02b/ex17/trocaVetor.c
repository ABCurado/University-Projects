#include "trocaVetor.h"

void trocaVetor(int * vetor1,int * vetor2,int size){
  int i, swap;
  for(i = 0 ; i < size ; i++){ //itera os vetores e recorrendo a um posção auxiliar de swap troca a ordem do vetores
        swap = *vetor1;
        *vetor1 = *vetor2;
        *vetor2 = swap;
    	vetor1++;
	    vetor2++;
  }
}
