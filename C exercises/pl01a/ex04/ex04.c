#include <stdio.h>
int main()
{

  int quantidade = 0 ,soma=0,n=0;
 printf("Qual é o primeiro numero a introduzir?") ;
 while (scanf("%d", &n) != (-1) ){ 
    quantidade++;
    soma += n;
    printf("A média dos numeros introduzidos é, %d\n",soma/quantidade);
    printf("Qual é o proximo numero a introduzir?") ;
  } 

  return 0;
}

