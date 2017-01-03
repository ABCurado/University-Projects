ex07: insere_dados.o main.o
	gcc -Wall -g insere_dados.o main.o -o ex07

main.o: main.c insere_dados.h
	gcc -c -g -Wall main.c -o main.o

insere_dados.o: insere_dados.s 
	gcc -c -g -Wall insere_dados.s -o insere_dados.o
