ex15: main.o
	gcc -Wall -g main.o -o ex15

factorial: factorial.o
	gcc -Wall -g factorial.o -o factorial

main.o: main.c 
	gcc -c -g -Wall main.c -o main.o

factorial.o: factorial.c 
	gcc -c -g -Wall factorial.c -o factorial.o

run: ex15 factorial clean
	./ex15

clean:
	rm -f main.o factorial.o

remove: clean
	rm -f ex15 factorial
