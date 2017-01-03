main: vec_pos.o main.o
	gcc -Wall -g vec_pos.o main.o -o main

vec_pos.o: vec_pos.s
	gcc -Wall -g -c vec_pos.s

main.o: main.c vec_pos.h
	gcc -Wall -g -c main.c -o main.o

run: main
	./main

clean:
	rm *.o main
