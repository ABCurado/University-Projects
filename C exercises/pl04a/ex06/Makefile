# makefile
main: main.o soma.o subtraccao.o aritmetica.o
	gcc -Wall -g main.o soma.o subtraccao.o aritmetica.o -o main

soma.o: soma.s
	gcc -Wall -g -c soma.s

subtraccao.o: subtraccao.s
	gcc -Wall -g -c subtraccao.s

aritmetica.o: variaveis.h soma.h subtraccao.h aritmetica.c
	gcc -Wall -g -c aritmetica.c -o aritmetica.o

main.o: main.c variaveis.h aritmetica.h
	gcc -Wall -g -c main.c -o main.o

run: main
	./main

clean:
	rm *.o main
