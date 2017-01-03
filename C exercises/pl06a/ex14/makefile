ex14: diferentes.o main.o
	gcc -Wall -g diferentes.o main.o -o ex14

main.o: main.c diferentes.h
	gcc -c -g -Wall main.c -o main.o

diferentes.o: diferentes.s
	gcc -c -g -Wall diferentes.s -o diferentes.o

