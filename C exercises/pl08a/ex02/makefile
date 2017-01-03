main: somatorio.o main.o
	gcc -Wall -g somatorio.o main.o -o main

somatorio.o: somatorio.s
	gcc -Wall -g -c somatorio.s

main.o: main.c somatorio.h
	gcc -Wall -g -c main.c -o main.o

run: main
	./main

clean:
	rm *.o main
