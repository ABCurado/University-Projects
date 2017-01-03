ex13: push.o pop.o main.o
	gcc -Wall -g push.o pop.o main.o -o ex13

main.o: main.c stack.h
	gcc -c -g -Wall main.c -o main.o

push.o: push.c 
	gcc -c -g -Wall push.c -o push.o

pop.o: pop.c
	gcc -c -g -Wall pop.c -o pop.o


