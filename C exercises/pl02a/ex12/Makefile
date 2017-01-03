# makefile
main: capitalizar.o main.o
	gcc -Wall -g capitalizar.o main.o -o main

capitalizar.o: capitalizar.c
	gcc -Wall -g -c capitalizar.c -o capitalizar.o

main.o: main.c capitalizar.h
	gcc -Wall -g -c main.c -o main.o
