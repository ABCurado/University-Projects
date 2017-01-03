main: decifra.o main.o
	gcc -Wall -g decifra.o main.o -o main

decifra.o: decifra.s
	gcc -Wall -g -c decifra.s

main.o: main.c decifra.h
	gcc -Wall -g -c main.c -o main.o

run: main
	./main

clean:
	rm *.o main
