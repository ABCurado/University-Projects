#include "funcoes.h"

void ordenaVetor(float* pointer,int size) {// mais uma vez o metodo bubble sort mas desta vez usando floats
  float *i, *j, swap;
  for(i = pointer; i < (pointer + size); i++){ 
    for(j = i + 1; j < (pointer + size); j++){ 
        if(*j < *i){ 
            swap = *i; 
            *i = *j;
            *j = swap;
          }
       }
   }
}

