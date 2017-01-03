/*Implemente uma função que pesquise uma palavra numa stringe devolva o índice da posição na qual a encontrou. A função deve encontrar palavras independentemente de serem maiúsculas ou minúsculas (considere que a stringapenas tem letras e espaços)*/
#include <stdio.h>
#include "pesquisaOcorrencias.h"

int main()
{
 char  string_teste1[]={"O  teste- testando o teste testavel ao testar o teste"};
 char string_teste2[]={"Arqcp é altamente"};
 char palavra_teste[]={"teste"};
 char * pointer_string = string_teste1;
 char * pointer_palavra= palavra_teste;
 int  vec[6]= {0,0,0,0,0,0} ;
 int * indice_palavras = vec;
 int resultado=0;


 printf("Na string %s \n ",pointer_string);
 printf("Palavra: %s\n",palavra_teste);
 printf("Primeira letra : %c,Primeira letra: %c ,indice: %d \n",*pointer_string,*pointer_palavra,*indice_palavras);
 resultado = pesquisa_ocorrencias_palavra(pointer_string,pointer_palavra,indice_palavras);

// pointer_palavra = palavra_teste; 
 printf("Foram encontradas %d ocorrencias da palavra %s (caso o numero seja -1 nao foram encontradas)\n",resultado,palavra_teste);

 pointer_string = string_teste2;
 pointer_palavra = palavra_teste;

 printf("Na string %s \n ",pointer_string);

 resultado = pesquisa_ocorrencias_palavra(pointer_string,pointer_palavra,indice_palavras);

 pointer_palavra = palavra_teste;
 printf("Foram encontradas %d ocorrencias da palavra %s (caso o numero seja -1 nao foram encontradas)\n",resultado,pointer_palavra); 
 
 return(0);
}
