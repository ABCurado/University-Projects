#include "testaInverso.h"

int concatena(char* string){
   char* i = string;
   char* j = string;
   int tamanho = 0; 
   while(*j != 0){ //Enquanto a string nao chega ao fim 
      *i = *j++; //Passa para o apontador i que vai registar os valores da string o valor do iterador j
      if(*i != ' '){ //caso a posiçao na seja um espaço
          i++;  //move o apontador para a proxima posição
	      tamanho++; //Incrementa um ao seu tamanho
      }
   }  
    *i = 0;
    return tamanho;
}

