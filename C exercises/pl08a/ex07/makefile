ex07:  conta_pares.o main.o
	gcc -Wall -g  conta_pares.o main.o -o ex07

main.o: main.c  conta_pares.h
	gcc -c -g -Wall main.c -o main.o

 conta_pares.o:  conta_pares.s
	gcc -c -g -Wall  conta_pares.s -o  conta_pares.o


