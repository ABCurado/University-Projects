/*Implemente uma função que, recebendo o endereço de dois vetores com amesma dimensão, troque  os  elementos  de  um  pelo  os  do  outro, usando  artimética  de apontadores*/
#include <stdio.h>
#include "trocaVetor.h"

int main()
{
 int vec1[10]={3,3,3,6,6,5,5,6,5,7};
 int vec2[10]={9,9,9,9,9,9,9,9,9,9};
 int vec3[10]={};
 int vec4[10]={1,2,3,4,5,6,7,8,9,0};
 int tamanho_vetores=10;
 int * ptr1 = vec1;  
 int * ptr2 = vec2;
 int * i;
 printf("O vetor 1 contem :");
 for (i = vec1; i < (tamanho_vetores + vec1); i++){
 	printf("%i ",*i);
 }

 printf("\nO vetor 2 contem :");
 for (i = vec2; i < (tamanho_vetores + vec2); i++){
 	printf("%i ",*i);
 }
 
 printf("\nTrocar vetores 1 e 2 ");
 trocaVetor(ptr1,ptr2,tamanho_vetores);

 printf("\nO vetor 1 contem :");
  for (i = vec1; i < (tamanho_vetores + vec1); i++){
 	printf("%i ",*i);
 }

 printf("\nO vetor 2 contem :");
 for (i = vec2; i < (tamanho_vetores + vec2); i++)
 {
 	printf("%i ",*i);
 }

 printf("\nO vetor 3 contem :");
  for (i = vec3; i < (tamanho_vetores + vec3); i++){
 	printf("%i ",*i);
 }

 printf("\nO vetor 4 contem :");
 for (i = vec4; i < (tamanho_vetores + vec4); i++){
 	printf("%i ",*i);
 }

 printf("\nTrocar vetores 3 e 4 ");
 trocaVetor(vec3,vec4,tamanho_vetores);

 printf("\nO vetor 3 contem :");
  for (i = vec3; i < (tamanho_vetores + vec3); i++){
 	printf("%i ",*i);
 }

 printf("\nO vetor 4 contem :");
 for (i = vec4; i < (tamanho_vetores + vec4); i++){
 	printf("%i ",*i);
 }
  return(0);
}
