main: str_copy_p.o main.o
	gcc -Wall -g str_copy_p.o main.o -o main

str_copy_p.o: str_copy_p.s
	gcc -Wall -g -c str_copy_p.s

main.o: main.c str_copy_p.h
	gcc -Wall -g -c main.c -o main.o

run: main
	./main

clean:
	rm *.o main
