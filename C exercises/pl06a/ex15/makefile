ex15: comuns.o main.o
	gcc -Wall -g comuns.o main.o -o ex15

main.o: main.c comuns.h
	gcc -c -g -Wall main.c -o main.o

comuns.o: comuns.s
	gcc -c -g -Wall comuns.s -o comuns.o

