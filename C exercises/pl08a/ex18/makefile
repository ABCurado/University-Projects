ex18:  soma_multiplos_x.o soma_multiplos_x_em_C.o main.o
	gcc -Wall -g  soma_multiplos_x.o soma_multiplos_x_em_C.o main.o -o ex18

main.o: main.c soma_multiplos_x.h
	gcc -c -g -Wall main.c -o main.o

soma_multiplos_x.o: soma_multiplos_x.s
	gcc -c -g -Wall soma_multiplos_x.s -o soma_multiplos_x.o

soma_multiplos_x_em_C.o:  soma_multiplos_x_em_C.c
	gcc -c -g -Wall soma_multiplos_x_em_C.c -o soma_multiplos_x_em_C.o
