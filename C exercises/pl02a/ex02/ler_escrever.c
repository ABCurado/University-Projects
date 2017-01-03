#include <stdio.h>

void ler(int * ap, int num) {
	int i;
	for (i = 0; i<num ; i++) {
		char str[100];
		scanf("%s", str);
		int num = toInt(str);
		*ap=num;
		ap++;
	}
}

void escrever(int * ap, int num) {
	int i;
	for (i = 0; i<num ; i++) {
		printf("Pos %d : %d\n", i, *ap);
		ap++;
	}
}
