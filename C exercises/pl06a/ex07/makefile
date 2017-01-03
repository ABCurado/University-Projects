main: vec_sum.o main.o
	gcc -Wall -g vec_sum.o main.o -o main

vec_sum.o: vec_sum.s
	gcc -Wall -g -c vec_sum.s

main.o: main.c vec_sum.h
	gcc -Wall -g -c main.c -o main.o

run: main
	./main

clean:
	rm *.o main
