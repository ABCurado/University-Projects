ex11:  rotate_left.o rotate_right.o main.o
	gcc -Wall -g  rotate_left.o rotate_right.o main.o -o ex11

main.o: main.c  rotate.h
	gcc -c -g -Wall main.c -o main.o

rotate_left.o:  rotate_left.c
	gcc -c -g -Wall  rotate_left.c -o  rotate_left.o

rotate_right.o:  rotate_right.c
	gcc -c -g -Wall  rotate_right.c -o  rotate_right.o

