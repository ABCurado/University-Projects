main: compare_string.o main.o
	gcc -Wall -g compare_string.o main.o -o main

compare_string.o: compare_string.s
	gcc -Wall -g -c compare_string.s

main.o: main.c compare_string.h
	gcc -Wall -g -c main.c -o main.o

run: main
	./main

clean:
	rm *.o main
