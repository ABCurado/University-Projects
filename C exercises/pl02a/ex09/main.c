/*Implemente uma função que recebe dois parâmetros: o endereço de um vetor de floats preenchido e o endereço de um segundo vetor de floats de igual dimensão, mas por preencher. Pretende-se que a função preencha, por ordem crescente, o.segundo vetor com os elementos que constam no primeiro vetor, eliminando os repetidos. A função deve retornar o número de elementos colocados no segundo vector. O conteúdo do segundo vector deve ser impresso na função main.*/
#include <stdio.h>
#include "funcoes.h"
int main()
{
 float  x[10]={10,324,3,1,1,5213,236,123,123,13};
 float y[10] ={};
 float * pointer_x = x;
 float * pointer_y = y;
 int  tamanho_novo_vetor;
 printf("Vetor:\n ");
 int i = 0;
 for( i = 0;i < 10 ; i++){ //Imprime vetor dado
        printf("%0.1f ",*(pointer_x+i)) ;
 } 
 printf("\n");
 tamanho_novo_vetor = organizaVetor(pointer_x,pointer_y,10); // Chama a funcao de ordenaçao
 printf("Vetor ordenado:\n");
 for( i = 0;i < tamanho_novo_vetor ; i++){ //Imprime vetor organizado
        printf("%0.1f ",*(pointer_y+i) );
 }
 printf("\n");
 return 0;
} 



