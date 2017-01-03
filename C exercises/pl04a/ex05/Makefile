# makefile
main: asm.o main.o
	gcc -Wall -g asm.o main.o -o main

asm.o: asm.s
	gcc -Wall -g -c asm.s

main.o: main.c asm.h
	gcc -Wall -g -c main.c -o main.o

run: main
	./main

clean:
	rm *.o main
