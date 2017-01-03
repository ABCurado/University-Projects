main: conta_bits_um.o main.o
	gcc -Wall -g conta_bits_um.o main.o -o main

conta_bits_um.o: conta_bits_um.s
	gcc -Wall -g -c conta_bits_um.s

main.o: main.c conta_bits_um.h
	gcc -Wall -g -c main.c -o main.o

run: main
	./main

clean:
	rm *.o main
