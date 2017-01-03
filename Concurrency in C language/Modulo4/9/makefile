ex: main.o
	gcc -Wall -g main.o -o ex -lrt -lpthread

main.o: main.c 
	gcc -c -g -Wall main.c -o main.o

run: ex
	./ex

clean:
	rm -f main.o
