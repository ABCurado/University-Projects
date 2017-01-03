main: preenche_vec.o main.o
	gcc -Wall -g preenche_vec.o main.o -o main

preenche_vec.o: preenche_vec.s
	gcc -Wall -g -c preenche_vec.s

main.o: main.c preenche_vec.h
	gcc -Wall -g -c main.c -o main.o

run: main
	./main

clean:
	rm *.o main
