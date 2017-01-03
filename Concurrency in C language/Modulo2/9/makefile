ex9: main.o
	gcc -Wall -g main.o -o ex9

main.o: main.c 
	gcc -c -g -Wall main.c -o main.o

run: ex9 clean
	./ex9

clean:
	rm -f main.o

remove: clean
	rm -f ex9
