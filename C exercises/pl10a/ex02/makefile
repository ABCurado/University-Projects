ex02: insere_dados.o main.o
	gcc -Wall -g insere_dados.o main.o -o ex02

main.o: main.c insere_dados.h Aluno.h
	gcc -c -g -Wall main.c -o main.o

insere_dados.o: insere_dados.c Aluno.h
	gcc -c -g -Wall insere_dados.c -o insere_dados.o

