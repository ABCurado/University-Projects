ex10: cria_matriz.o procura_matriz.o main.o
	gcc -Wall -g cria_matriz.o procura_matriz.o main.o -o ex10

main.o: main.c procura_matriz.h
	gcc -c -g -Wall main.c -o main.o

cria_matriz.o: cria_matriz.c 
	gcc -c -g -Wall cria_matriz.c -o cria_matriz.o

procura_matriz.o: procura_matriz.c 
	gcc -c -g -Wall procura_matriz.c -o procura_matriz.o


