#include <stdio.h>
#include "testaInverso.h"

void paraMaiusculas(char* string) {

   while(*string){         //Enquanto o apontador nao aponte para null   
       if ( *string >= 'a' && *string <= 'z' ){  //Caso seja uma letra minuscula 
          *string = *string - 32;               //Em codigo ASCII as maiusculas distam 32 das minusculas
       }
       string++; 	//Adiciona ao apontador, para ele apontar para o proximo apontador
   }
}

