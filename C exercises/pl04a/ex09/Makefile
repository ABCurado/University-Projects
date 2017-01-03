# makefile
main: main.o jogo.o
	gcc -Wall -g main.o jogo.o -o main

jogo.o: jogo.s
	gcc -Wall -g -c jogo.s

main.o: main.c jogo.h
	gcc -Wall -g -c main.c -o main.o

run: main
	./main

clean:
	rm *.o main
