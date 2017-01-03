#include <stdio.h>
#include "f1.h" 
#include "f2.h"
#include "f3.h"
#include "f4.h"
int i=0,j=0;

int main(void) {
	i = 10;
	j = 0;
	printf("i = %d j = %d:\n",i,j);
	printf("f1 = %d\n",f1());
	printf("f2 = %d\n",f2());
	printf("f3 = %d\n",f3());
	printf("f4 = %d\n",f4());	

	i = 46;
	j = 4;
	printf("i = %d j = %d:\n",i,j);
	printf("f1 = %d\n",f1());
	printf("f2 = %d\n",f2());
	printf("f3 = %d\n",f3());
	printf("f4 = %d\n",f4());	
		
	i = 12;
	j = 4;
	printf("i = %d j = %d:\n",i,j);
	printf("f1 = %d\n",f1());
	printf("f2 = %d\n",f2());
	printf("f3 = %d\n",f3());
	printf("f4 = %d\n",f4());	

	i = 5;
	j = 5;
	printf("i = %d j = %d:\n",i,j);
	printf("f1 = %d\n",f1());
	printf("f2 = %d\n",f2());
	printf("f3 = %d\n",f3());
	printf("f4 = %d\n",f4());	
	return 0;
}

