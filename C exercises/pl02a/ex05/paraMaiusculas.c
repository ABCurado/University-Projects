#include <stdio.h>
#include "paraMaiusculas.h"

void paraMaiusculas(char* string) {
   char * inicio = string;  //Sitio onde fica guardado a primeira posiçao da String
   while(*string){         //Enquanto o apontado nao aponte para null   
       if ( *string >= 'a' && *string <= 'z' ){  //Caso seja uma letra minuscula 
          *string = *string - 32;               //Em codigo ASCII as maiusculas distam 32 das minusculas
       }
       string++; 	//Adiciona ao apontador, para ele apontar para o proximo apontador
   }
}

