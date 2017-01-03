main: activa_bit.o main.o
	gcc -Wall -g activa_bit.o main.o -o main

activa_bit.o: activa_bit.s
	gcc -Wall -g -c activa_bit.s

main.o: main.c activa_bit.h
	gcc -Wall -g -c main.c -o main.o

run: main
	./main

clean:
	rm *.o main
