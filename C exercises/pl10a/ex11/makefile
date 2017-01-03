main: conta_pares_matriz.o imprime_matriz.o cria_matriz.o main.o
	gcc -Wall -g conta_pares_matriz.o imprime_matriz.o cria_matriz.o main.o -o main

conta_pares_matriz.o: conta_pares_matriz.s
	gcc -Wall -g -c conta_pares_matriz.s

imprime_matriz.o: imprime_matriz.c
	gcc -Wall -g -c imprime_matriz.c

cria_matriz.o: cria_matriz.c
	gcc -Wall -g -c cria_matriz.c

main.o: main.c imprime_matriz.h cria_matriz.h conta_pares_matriz.h
	gcc -Wall -g -c main.c -o main.o

run: main
	./main

clean:
	rm *.o main
