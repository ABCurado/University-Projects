/*Implemente uma função que seja capaz de identificar se duas frases, cujos endereços são recebidos por parâmetro,podem ser lidas da mesma forma, de trás para a frente ou de frente para trás. A função deve retornar 1 ou 0, consoante as frases possam ou não ser lidas de forma igual, respectivamente.*/ 
#include <stdio.h>
#include "testaInverso.h"
int main(){ 
 char teste1[] = "Anotaram  a  data  da  maratona";
 char teste2[] = "Arqcp e Altamente!";
 char teste3[] = "Never odd or even";
 int flag;
 printf("Verificaçao se as strings podem ser escritas ao contrario: \n");
 flag = testaInverso(teste1);
 printf("A string %s -- %s \n",teste1,flag?"true":"false");
 flag = testaInverso(teste2);
 printf("A string %s -- %s \n",teste2,flag?"true":"false");
 flag = testaInverso(teste3);
 printf("A string %s -- %s \n",teste3,flag?"true":"false");
 return(0);
} 



