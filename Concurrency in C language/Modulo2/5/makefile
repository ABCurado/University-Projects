ex5: main.o
	gcc -Wall -g main.o -o ex5

main.o: main.c 
	gcc -c -g -Wall main.c -o main.o

run: ex5 clean
	./ex5

clean:
	rm -f main.o

remove: clean
	rm -f ex5
