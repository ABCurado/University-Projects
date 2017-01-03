main: space_count.o main.o
	gcc -Wall -g space_count.o main.o -o main

space_count.o: space_count.s
	gcc -Wall -g -c space_count.s

main.o: main.c space_count.h
	gcc -Wall -g -c main.c -o main.o

run: main
	./main

clean:
	rm *.o main
