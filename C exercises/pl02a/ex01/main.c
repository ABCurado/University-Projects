#include <stdio.h>
int main()
{
 int x=5;
 int* xPtr = &x;
 float y = *xPtr + 3 ;
 int vec[] = {10,11,12,13}; 
 printf("x= %d , y= %f \n", x, y ) ;
 printf("Endereço de x= %p ,Endereço  xPtr= %p \n", &x, &*xPtr ) ; 
 printf("Valor para onde xPtr aponta = %d \n",*xPtr ) ; 
 printf("Endereço de vec = %p \n",&vec ) ; 
 printf("Valor de vec[0]=%d,vec[1]=%d,vec[2]=%d,vec[3]=%d \n",vec[0],vec[1],vec[2],vec[3] ) ; 
 printf("Endereços de  vec[0]=%p,vec[1]=%p,vec[2]=%p,vec[3]=%p \n",&vec[0],&vec[1],&vec[2],&vec[3] ) ; 
 //b)O endereço de vec aponta para vec[0] e vec[1],vec[2] e vec[3] aponta para as posiçoes consequentes
 //c)Esperaria ver endereços diferentes pois é o sistema operativo que trata da alocaçao de memória
 return 0;
}
