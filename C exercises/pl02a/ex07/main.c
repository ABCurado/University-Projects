/*Implemente uma função que, recebendo o endereço de um vetor de inteiros, ordene esse mesmo vetor por ordem ascendente. A função não deve ter retorno.O novo conteúdo do vetor deve ser impresso na função main.*/
#include <stdio.h>
#include "ordenaVetor.h"
int main()
{
 int x[10]={10,324,3,21,1,5213,236,123,532,13};
 int * pointer = x;
 printf("Vetor:");
 int i = 0;
 for( i = 0;i < 10 ; i++){
    printf("%d ", *(pointer+i));
 } 
 printf("\n");
 ordenaVetor(pointer,10);
 printf("Vetor ordenado:");
 for( i = 0;i < 10 ; i++){
    printf("%d ", *(pointer+i));
 }
 printf("\n");
 return 0;
} 


