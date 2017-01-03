main: imprime_matriz.o cria_matriz.o main.o
	gcc -Wall -g imprime_matriz.o cria_matriz.o main.o -o main

imprime_matriz.o: imprime_matriz.c
	gcc -Wall -g -c imprime_matriz.c

cria_matriz.o: cria_matriz.c
	gcc -Wall -g -c cria_matriz.c

main.o: main.c imprime_matriz.h cria_matriz.h
	gcc -Wall -g -c main.c -o main.o

run: main
	./main

clean:
	rm *.o main
