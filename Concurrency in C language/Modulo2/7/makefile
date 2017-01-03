ex7: main.o
	gcc -Wall -g main.o -o ex7

main.o: main.c 
	gcc -c -g -Wall main.c -o main.o

run: ex7 clean
	./ex7

clean:
	rm -f main.o

remove: clean
	rm -f ex7
