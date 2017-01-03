ex06: str_cat.o main.o
	gcc -Wall -g str_cat.o main.o -o ex06

main.o: main.c str_cat.h
	gcc -c -g -Wall main.c -o main.o

str_cat.o: str_cat.s
	gcc -c -g -Wall str_cat.s -o str_cat.o


