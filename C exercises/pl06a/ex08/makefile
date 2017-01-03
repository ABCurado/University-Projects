ex08: vec_inc.o main.o
	gcc -Wall -g vec_inc.o main.o -o ex08

main.o: main.c vec_inc.h
	gcc -c -g -Wall main.c -o main.o

vec_inc.o: vec_inc.s
	gcc -c -g -Wall vec_inc.s -o vec_inc.o


