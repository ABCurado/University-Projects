ex09:  calc.o main.o
	gcc -Wall -g  calc.o main.o -o ex09

main.o: main.c  calc.h
	gcc -c -g -Wall main.c -o main.o

 calc.o:  calc.s
	gcc -c -g -Wall  calc.s -o  calc.o


