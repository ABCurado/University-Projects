ex04: encripta.o main.o
	gcc -Wall -g encripta.o main.o -o ex04

main.o: main.c encripta.h
	gcc -c -g -Wall main.c -o main.o

encripta.o: encripta.s
	gcc -c -g -Wall encripta.s -o encripta.o


