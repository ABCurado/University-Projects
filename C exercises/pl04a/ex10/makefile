ex10:soma.o main.o
	gcc -Wall -g soma.o main.o -o ex10

main.o: main.c soma.h
	gcc -c -g -Wall main.c -o main.o

soma.o: soma.s
	gcc -c -g -Wall soma.s -o soma.o


