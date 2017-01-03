#include "soma.h"
#include "subtraccao.h"
#include "variaveis.h"

void somar() {
	res=op2;
	soma();
}

void multiplicar() {
	int i;
	for (i=op2; i>0; i--) {
		soma();
	}
}

void subtrair() {
	res=op1;
	op1=op2;
	subtraccao();
}

void dividir() {
	int count=0;
	res=op1;
	op1=op2;

	while (res >= op2) {
		subtraccao();
		count++;
	}
	resto=res;
	res=count;
}
