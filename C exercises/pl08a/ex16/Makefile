main: altera.o main.o
	gcc -Wall -g altera.o main.o -o main

altera.o: altera.s
	gcc -Wall -g -c altera.s

main.o: main.c altera.h
	gcc -Wall -g -c main.c -o main.o

run: main
	./main

clean:
	rm *.o main
