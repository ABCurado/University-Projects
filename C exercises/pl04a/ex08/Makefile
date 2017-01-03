# makefile
main: main.o somatorio.o
	gcc -Wall -g main.o somatorio.o -o main

somatorio.o: somatorio.s
	gcc -Wall -g -c somatorio.s

main.o: main.c somatorio.s
	gcc -Wall -g -c main.c -o main.o

run: main
	./main

clean:
	rm *.o main
