# makefile
main: converterToLow.o main.o
	gcc -Wall -g converterToLow.o main.o -o main

converterToLow.o: converterToLow.c
	gcc -Wall -g -c converterToLow.c -o converterToLow.o

main.o: main.c converterToLow.h
	gcc -Wall -g -c main.c -o main.o
