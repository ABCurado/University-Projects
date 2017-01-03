ex: main.o
	gcc -Wall -g main.o -o ex -lrt -lpthread

main.o:
	gcc -c -g -Wall main.c -o main.o

run: ex clean
	 echo "Execute ./ex" & ./ex

clean:
	rm -f main.o

remove:
	sudo rm -f main /dev/shm/s*
