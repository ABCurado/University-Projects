main: imprime.o calcula.o main.o
	gcc -Wall -g imprime.o calcula.o main.o -o main

calcula.o: calcula.s
	gcc -Wall -g -c calcula.s

imprime.o: imprime.c
	gcc -Wall -g -c imprime.c

main.o: main.c imprime.h calcula.h
	gcc -Wall -g -c main.c -o main.o

run: main
	./main

clean:
	rm *.o main
