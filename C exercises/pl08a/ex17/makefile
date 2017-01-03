ex17:  somabyte2.o somabyte2_em_C.o main.o
	gcc -Wall -g  somabyte2.o somabyte2_em_C.o main.o -o ex17

main.o: main.c somabyte2.h
	gcc -c -g -Wall main.c -o main.o

somabyte2.o: somabyte2.s
	gcc -c -g -Wall somabyte2.s -o somabyte2.o

somabyte2_em_C.o:  somabyte2_em_C.c
	gcc -c -g -Wall somabyte2_em_C.c -o somabyte2_em_C.o

