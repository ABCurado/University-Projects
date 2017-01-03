# makefile
main: main.o testes.o
	gcc -Wall -g main.o testes.o -o main

testes.o: testes.s
	gcc -Wall -g -c testes.s

main.o: main.c testes.h
	gcc -Wall -g -c main.c -o main.o

run: main
	./main

clean:
	rm *.o main
