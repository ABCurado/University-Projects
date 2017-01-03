ex04: insere_notas.o insere_dados.o main.o
	gcc -Wall -g insere_notas.o insere_dados.o main.o -o ex04

main.o: main.c insere_notas.h
	gcc -c -g -Wall main.c -o main.o

insere_notas.o: insere_notas.s insere_notas.h 
	gcc -c -g -Wall insere_notas.s -o insere_notas.o

insere_dados.o: insere_dados.c insere_notas.h 
	gcc -c -g -Wall insere_dados.c -o insere_dados.o
