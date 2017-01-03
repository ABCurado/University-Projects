main: str_copy_p2.o main.o
	gcc -Wall -g str_copy_p2.o main.o -o main

str_copy_p2.o: str_copy_p2.s
	gcc -Wall -g -c str_copy_p2.s

main.o: main.c str_copy_p2.h
	gcc -Wall -g -c main.c -o main.o

run: main
	./main

clean:
	rm *.o main
