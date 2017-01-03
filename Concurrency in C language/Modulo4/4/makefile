ex: main.o
	gcc -Wall -g main.o -o ex -lpthread

main.o: main.c 
	gcc -c -g -Wall main.c -o main.o

run: ex clean
	./ex test.txt & ./ex test.txt & ./ex test.txt & ./ex test.txt & ./ex test.txt & ./ex test.txt

clean:
	rm -f main.o

remove: clean
	rm -f ex
