# makefile
ex02: ler_escrever.o conversao.o ex02.o
	gcc -Wall -g ler_escrever.o conversao.o ex02.o -o ex02
	
ler_escrever.o: ler_escrever.c
	gcc -Wall -g -c ler_escrever.c -o ler_escrever.o
	
conversao.o: conversao.c
	gcc -Wall -g -c conversao.c -o conversao.o
	
ex02.o: ex02.c ler_escrever.h conversao.h
	gcc -Wall -g -c ex02.c -o ex02.o
