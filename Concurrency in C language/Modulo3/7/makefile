ex: main.o
	gcc -Wall -g main.o -o ex -lrt

main.o: main.c 
	gcc -c -g -Wall main.c -o main.o

run: ex clean
	./ex

clean:
	rm -f main.o

remove: clean
	rm -f ex
