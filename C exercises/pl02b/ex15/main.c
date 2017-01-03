/*Implemente um programa que preencha um vetor de 100 números inteiros com valores aleatórios entre 0 e 20. De seguida o programa deve determinar quantos conjuntos de três elementos crescentes existem no vetor(vi< vi+1< vi+2).Deverá criar uma função para preencher o vetor, outrapara comparar os três elementos e outra para contabilizar o número deexistências. Cada uma das funções deve ser colocada num ficheiro decódigo fonte separado, além do ficheiro principal*/
#include <stdio.h>
#include "funcoes.h"
#include <time.h>
#include <stdlib.h>

int main()
	{
 srand(time(NULL));		// usando esta funçao é possivel obter numeros aleatorios a cada execuço do programa
 int vec[100]={};
 int resultado,tamanho=100;
 resultado = preencheEConta(vec,tamanho);
 printf("Foram encontradas %i ocorrencias\n",resultado);
 return(0);
}
