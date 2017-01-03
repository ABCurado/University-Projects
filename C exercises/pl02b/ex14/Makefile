# makefile
main: frequencia.o ordenaVetor.o main.o
	gcc -Wall -g frequencia.o ordenaVetor.o main.o -o main

frequencia.o: frequencia.c
	gcc -Wall -g -c frequencia.c -o frequencia.o

ordenaVetor.o: ordenaVetor.c
	gcc -Wall -g -c ordenaVetor.c -o ordenaVetor.o

main.o: main.c frequencia.h
	gcc -Wall -g -c main.c -o main.o
