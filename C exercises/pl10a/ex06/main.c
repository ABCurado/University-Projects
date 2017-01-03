#include <stdio.h>
#include "insere_dados.h" 

void imprimirS1(s2 *p){
	printf("w = %d , %d, %d\n",p -> w[0],p -> w[1],p -> w[2]);
	printf("j = %d\n",p -> j);
	printf("c = %c, %c, %c\n",p -> c[0],p -> c[1],p -> c[2]);
}

int main(void) {
	s2 struct1;
	s2 * p;
	p = &struct1;
	short int w[3] = {1,3,3};
	int j = 3;
	char c[3] = {'e','e','t'};

	insere_dados(p,w,j,c);

	imprimirS1(p);

	return 0;
}
