#include <stdlib.h>
#include "paraMaiusculas.h"

char* paraMaiusculas(char* string) {
   int contador = 0,novoContador = 0;
   char * novaString  = (char*) malloc(sizeof(char)); 
   char * temp = NULL; 
   while(*(string+contador)){     
	   if (*(string+contador) != ' '){
			temp = (char*) realloc(novaString, (contador+1)+sizeof(char));
			if(temp != NULL){
				novaString = temp;
				temp = NULL;
			}
			if ( *(string+contador) >= 'a' && *(string+contador) <= 'z' ){ 
				*(novaString+novoContador) = *(string+contador) - 32;
				novoContador++;
       		}else{
				*(novaString+novoContador) = *(string+contador);		
				novoContador++;	
			}
		}
       contador++; 	
   }
	*(novaString+novoContador) = 0;	
	return novaString;
}

