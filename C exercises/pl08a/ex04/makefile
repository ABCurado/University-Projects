main: soma_e_maior.o main.o
	gcc -Wall -g soma_e_maior.o main.o -o main

soma_e_maior.o: soma_e_maior.s
	gcc -Wall -g -c soma_e_maior.s

main.o: main.c soma_e_maior.h
	gcc -Wall -g -c main.c -o main.o

run: main
	./main

clean:
	rm *.o main
