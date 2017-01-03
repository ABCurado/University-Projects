ex05:  quadrado.o incrementa_e_quadrado.o main.o
	gcc -Wall -g  quadrado.o incrementa_e_quadrado.o main.o -o ex05

main.o: main.c  incrementa_e_quadrado.h
	gcc -c -g -Wall main.c -o main.o

quadrado.o:  quadrado.s
	gcc -c -g -Wall  quadrado.s -o  quadrado.o

incrementa_e_quadrado.o:  incrementa_e_quadrado.s
	gcc -c -g -Wall  incrementa_e_quadrado.s -o  incrementa_e_quadrado.o
