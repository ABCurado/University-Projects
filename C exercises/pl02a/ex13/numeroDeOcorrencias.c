#include "numeroDeOcorrencias.h"
#include <stdio.h>
void ocorrenciasDeCaratere(char frase[] , char caratere ,int ocorrencias[]){
    int j= 0 , i = 0 ;
    while(frase[i] != 0){ //enquanto a frase nao chega ao fim 
        if(frase[i] == caratere || frase[i] == (caratere-32)){//se o carater da frase for igual ao pretendido
            ocorrencias[j] = i; //adiciona-se a posiçao do vetor aovetro que indica as posiçoes
            j++;	//incrementa-se 1 ao vetor
         }
         i++;
    }
}

