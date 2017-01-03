ex10: main.o
	gcc -Wall -g main.o -o ex10

main.o: main.c 
	gcc -c -g -Wall main.c -o main.o

run: ex10
	./ex10

clean:
	rm -f main.o
