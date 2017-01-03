/*Implemente uma função que recebe três parâmetros:o endereço de um string(str), um caractere (c) e um endereçeo de um vetor de inteiros (vec). A função deverá percorrer str à procura do caractere c. Sempre que a função encontra o caractere pretendido, acrescenta a vec a posição onde o caractere foi encontrado na string.
Nota: O tamanho do vetor de inteiros é da responsabilidade de quem invoca a funcao*/
#include <stdio.h>
#include "numeroDeOcorrencias.h"
int main()
{
 char frase[]={"Arqcp é Altamente"};
 int ocorrencias[10] ={};
 char caratere = 'a';
 ocorrenciasDeCaratere(frase,caratere,ocorrencias);
 printf("A string introduzida foi: %s \n",frase);
 printf("O caratere %c aparece nas posiçoes:",caratere);
 int i = 0;
 for( i = 0;i < 10 ; i++){
	if(i == 0 || ocorrencias[i] != 0) 
        printf("%d ",ocorrencias[i]);
 }
 printf("\n");
 return 0;
} 



