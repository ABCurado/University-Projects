ex11: cria_matriz.o soma_matrizes.o main.o
	gcc -Wall -g cria_matriz.o soma_matrizes.o main.o -o ex11

main.o: main.c soma_matrizes.h
	gcc -c -g -Wall main.c -o main.o

cria_matriz.o: cria_matriz.c 
	gcc -c -g -Wall cria_matriz.c -o cria_matriz.o

soma_matrizes.o: soma_matrizes.c
	gcc -c -g -Wall soma_matrizes.c -o soma_matrizes.o


