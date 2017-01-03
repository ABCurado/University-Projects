ex18: main.o
	gcc -Wall -g main.o -o ex18

main.o: main.c 
	gcc -c -g -Wall main.c -o main.o

run: ex18 clean
	./ex18

clean:
	rm -f main.o

remove: clean
	rm -f ex18