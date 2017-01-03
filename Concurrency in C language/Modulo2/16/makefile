ex: main.o
	gcc -Wall -g main.o -o injecta

main.o: main.c 
	gcc -c -g -Wall main.c -o main.o

run: ex
	./injecta

clean:
	rm -f main.o
