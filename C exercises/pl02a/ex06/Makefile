# makefile
main: potencia.o main.o
	gcc -Wall -g potencia.o main.o -o main

converterToLow.o: potencia.c
	gcc -Wall -g -c potencia.c -o potencia.o

main.o: main.c potencia.h
	gcc -Wall -g -c main.c -o main.o
