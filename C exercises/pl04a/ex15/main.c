#include <stdio.h>
#include "QuenteFrio.h"

short temp_atual,temp_pretendida,temp;

int main(void) {

	temp_atual = 10;
	temp_pretendida = 1;
	printf("A = %d B = %d :\n",temp_atual,temp_pretendida);
 	QuenteFrio();
	printf("O resultado é é: %d minutos \n",temp);

	temp_atual = 10;
	temp_pretendida = 20;
	printf("A = %d B = %d :\n",temp_atual,temp_pretendida);
 	QuenteFrio();
	printf("O resultado é é: %d minutos \n",temp);	
	return 0;
}

