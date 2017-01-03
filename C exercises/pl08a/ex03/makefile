main: menor.o main.o
	gcc -Wall -g menor.o main.o -o main

menor.o: menor.s
	gcc -Wall -g -c menor.s

main.o: main.c menor.h
	gcc -Wall -g -c main.c -o main.o

run: main
	./main

clean:
	rm *.o main
