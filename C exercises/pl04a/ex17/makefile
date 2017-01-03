ex17:multiplo.o main.o
	gcc -Wall -g multiplo.o main.o -o ex17

main.o: main.c multiplo.h
	gcc -c -g -Wall main.c -o main.o

operacoes.o: multiplo.s
	gcc -c -g -Wall multiplo.s -o multiplo.o


