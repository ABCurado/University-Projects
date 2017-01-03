#include <stdio.h>
int main()
{

  char  frase[256]; 
 int count=0,contador=0;
 printf("Intoduza a frase: ") ;
 fgets(frase, sizeof(frase), stdin);
 while(frase[count] != 0){
 	if(frase[count]==' '){
		 contador++;
	 }
	 count++; 
 }
 printf("A sua frase tem %d espaços \n",contador);
 return 0;
}
