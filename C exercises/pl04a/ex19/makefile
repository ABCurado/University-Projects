# makefile
main: main.o sal.o
	gcc -Wall -g main.o sal.o -o main

sal.o: sal.s
	gcc -Wall -g -c sal.s

main.o: main.c sal.h
	gcc -Wall -g -c main.c -o main.o

run: main
	./main

clean:
	rm *.o main
