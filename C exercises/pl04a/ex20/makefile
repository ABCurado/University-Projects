ex20:f1.o f2.o f3.o f4.o main.o
	gcc -Wall -g f1.o f2.o f3.o f4.o main.o -o ex20

main.o: main.c f1.h f2.h f3.h f4.h
	gcc -c -g -Wall main.c -o main.o

f1.o: f1.s
	gcc -c -g -Wall f1.s -o f1.o

f2.o: f2.s
	gcc -c -g -Wall f2.s -o f2.o

f3.o: f3.s
	gcc -c -g -Wall f3.s -o f3.o

f4.o: f4.s
	gcc -c -g -Wall f4.s -o f4.o




