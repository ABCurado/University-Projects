/*Implemente um a função que, dado o um vetor, imprima todos os seus	elementos pares. A função deverá receber dois parâmetros: o endereço de um vetor de inteiros e um inteiro que indica quantos elementos desse vetor estão preenchidos*/

#include <stdio.h>
#include "imprimePares.h"

int main()
{
 int x[20]={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14};
 int * pointer = x;
 imprimePares(pointer,14);

 return 0;
} 


