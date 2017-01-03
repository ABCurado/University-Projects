main: main.o
	gcc -Wall -g main.o -o main

main.o: main.c aluno.h
	gcc -Wall -g -c main.c -o main.o

run: main
	./main

clean:
	rm *.o main
