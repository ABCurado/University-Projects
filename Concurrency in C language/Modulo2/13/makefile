ex13: main.o
	gcc -Wall -g main.o -o ex13

main.o: main.c 
	gcc -c -g -Wall main.c -o main.o

run: ex13 clean
	./ex13

clean:
	rm -f main.o

remove: clean
	rm -f ex13
