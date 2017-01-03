ex11: main.o
	gcc -Wall -g main.o -o ex11

main.o: main.c 
	gcc -c -g -Wall main.c -o main.o

run: ex11
	./ex11

clean:
	rm -f main.o
