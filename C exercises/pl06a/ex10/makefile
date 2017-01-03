ex10: vec_search.o main.o
	gcc -Wall -g vec_search.o main.o -o ex10

main.o: main.c vec_search.h
	gcc -c -g -Wall main.c -o main.o

vec_search.o: vec_search.s
	gcc -c -g -Wall vec_search.s -o vec_search.o


