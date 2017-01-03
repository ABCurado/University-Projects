#include <stdio.h>

 
int toInt(char input[]) {
  int c, output = 0;
 
  for (c = 0; input[c] != 0; c++) {
    output = output * 10 + input[c] - '0';
  }
 
  return output;
}

int calcularMedia(int num1,int num2){
	return (num1+num2)/2;
}

int main()
{
  char num1[128],num2[128];
  int resultado,n1,n2;
 
  printf("Introduza os numeros para calcualr a media separados por um espaço\n");
  scanf("%s %s", num1, num2);
 
  n1 = toInt(num1);
  n2 = toInt(num2);
  
  resultado = calcularMedia(n1,n2);
 
  printf("Resultado %d\n", resultado);
 
  return 0;
}
