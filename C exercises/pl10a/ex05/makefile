main: maiores.o aluno.o insere_notas.o main.o
	gcc -Wall -g maiores.o aluno.o insere_notas.o main.o -o main

maiores.o: maiores.c
	gcc -Wall -g -c maiores.c

insere_notas.o: insere_notas.s
	gcc -Wall -g -c insere_notas.s

aluno.o: aluno.c aluno.h
	gcc -Wall -g -c aluno.c

main.o: main.c aluno.h insere_notas.h maiores.h
	gcc -Wall -g -c main.c -o main.o

run: main
	./main

clean:
	rm *.o main
