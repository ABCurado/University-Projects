main: aluno.o altera_morada.o main.o
	gcc -Wall -g aluno.o altera_morada.o main.o -o main

altera_morada.o: altera_morada.s
	gcc -Wall -g -c altera_morada.s

aluno.o: aluno.c aluno.h
	gcc -Wall -g -c aluno.c

main.o: main.c aluno.h altera_morada.h
	gcc -Wall -g -c main.c -o main.o

run: main
	./main

clean:
	rm *.o main
