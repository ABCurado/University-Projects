ex20: batota main.o
	gcc -Wall -g main.o -o ex20

main.o: main.c 
	gcc -c -g -Wall main.c -o main.o

batota: batota.o
	gcc-Wall -g batota.o -o batota

batota.o: batota.c 
	gcc -c -g -Wall batota.c -o batota.o

run: ex20 clean
	./ex20

clean:
	rm -f main.o batota.o

remove: clean
	rm -f ex20