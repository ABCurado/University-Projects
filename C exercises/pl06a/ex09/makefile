main: vecsum_par.o main.o
	gcc -Wall -g vecsum_par.o main.o -o main

vecsum_par.o: vecsum_par.s
	gcc -Wall -g -c vecsum_par.s

main.o: main.c vecsum_par.h
	gcc -Wall -g -c main.c -o main.o

run: main
	./main

clean:
	rm *.o main
