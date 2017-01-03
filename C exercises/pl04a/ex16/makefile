# makefile
main: main.o soma.o subtraccao.o multiplicacao.o divisao.o
	gcc -Wall -g main.o soma.o subtraccao.o multiplicacao.o divisao.o -o main

soma.o: soma.s
	gcc -Wall -g -c soma.s

subtraccao.o: subtraccao.s
	gcc -Wall -g -c subtraccao.s

multiplicacao.o: multiplicacao.s
	gcc -Wall -g -c multiplicacao.s

divisao.o: divisao.s
	gcc -Wall -g -c divisao.s

main.o: main.c soma.h subtraccao.h multiplicacao.h divisao.h
	gcc -Wall -g -c main.c -o main.o

run: main
	./main

clean:
	rm *.o main
