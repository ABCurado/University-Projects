#include <stdio.h>
#include <time.h>
#include <stdlib.h>

int main (void){
	int max = 5;
	int min = 1;
	srand(time(NULL));
	int randomValue = (rand()%(max-min))+min;
	exit(randomValue);
}
