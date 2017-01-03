ex01:  quadrado.o main.o
	gcc -Wall -g  quadrado.o main.o -o ex01

main.o: main.c  quadrado.h
	gcc -c -g -Wall main.c -o main.o

 quadrado.o:  quadrado.s
	gcc -c -g -Wall  quadrado.s -o  quadrado.o


