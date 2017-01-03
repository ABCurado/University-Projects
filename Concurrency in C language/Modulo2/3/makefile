ex3: main.o
	gcc -Wall -g main.o -o ex3

main.o: main.c 
	gcc -c -g -Wall main.c -o main.o

run: ex3 clean
	./ex3

clean:
	rm -f main.o

remove: clean
	rm -f ex3
