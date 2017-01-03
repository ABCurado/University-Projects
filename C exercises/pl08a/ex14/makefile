main: intBin.o junta_bits.o main.o
	gcc -Wall -g intBin.o junta_bits.o main.o -o main

intBin.o: intBin.c
	gcc -Wall -g -c intBin.c

junta_bits.o: junta_bits.s
	gcc -Wall -g -c junta_bits.s

main.o: main.c junta_bits.h intBin.h
	gcc -Wall -g -c main.c -o main.o

run: main
	./main

clean:
	rm *.o main
