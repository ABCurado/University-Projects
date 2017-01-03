ex13:operacoes.o main.o
	gcc -Wall -g operacoes.o main.o -o ex13

main.o: main.c operacoes.h
	gcc -c -g -Wall main.c -o main.o

operacoes.o: operacoes.s
	gcc -c -g -Wall operacoes.s -o operacoes.o


