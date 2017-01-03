ex19: main.o
	gcc -Wall -g main.o -o ex19

main.o: main.c 
	gcc -c -g -Wall main.c -o main.o

run: ex19 clean
	./ex19

clean:
	rm -f main.o

remove: clean
	rm -f ex19