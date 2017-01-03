#include <stdio.h>
#include "sal.h"

int salario=0;
int codigo=0;
int res=0;


int main(void) {

	salario=1000;
	codigo=101;
	sal();
	printf("\n%d de %d aumenta para %d\n",codigo,salario,res);

        salario=1500;
        codigo=102;
        sal();
        printf("\n%d de %d aumenta para %d\n",codigo,salario,res);

        salario=800;
        codigo=103;
        sal();
        printf("\n%d de %d aumenta para %d\n",codigo,salario,res);

        salario=500;
        codigo=90;
        sal();
        printf("\n%d de %d aumenta para %d\n",codigo,salario,res);
	return 0;
}
