/*4.Implemente  uma função  que,  recebendo  o  endereço  de  uma string, troque  todas  as  letras  minúsculas  para  maiúsculas.  A  função  nãodeve  utilizar  outras  funções  disponíveis nas  bibliotecas  do C.
5.Implemente  uma  nova  função  que  resolva  o  exercício anterior utilizando  aritmética de apontadores*/

#include <stdio.h>
#include "paraMaiusculas.h"
int main()
{
 char string[]={"Arqcp e Altamente!!"};
 char * pointer = string;
 printf("String introduzida : %s \n",pointer);
 paraMaiusculas(pointer);
 printf("String em maiusculas : %s \n",pointer);

 return 0;
} 


