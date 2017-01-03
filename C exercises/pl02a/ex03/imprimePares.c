#include "stdio.h"
#include "imprimePares.h"

void imprimePares(int* pointer,int numeroDeCarateres) {
  int i=0;
  printf("Os numeros pares são: ");
  for(i=0;i <=  numeroDeCarateres;i++) { //ciclo para correr as posiçoes no apontador
    if(!(*(pointer+i) % 2)){  //Caso o numero contido no apontador+i seja par
      printf("%d ",*(pointer+i));
    }
  }
  printf("\n");
}

