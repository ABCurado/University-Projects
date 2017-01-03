ex15:  menor_data.o menor_data_em_C.o main.o
	gcc -Wall -g  menor_data.o menor_data_em_C.o main.o -o ex15

main.o: main.c menor_data.h
	gcc -c -g -Wall main.c -o main.o

menor_data.o: menor_data.s
	gcc -c -g -Wall menor_data.s -o menor_data.o

menor_data_em_C.o:  menor_data_em_C.c
	gcc -c -g -Wall menor_data_em_C.c -o menor_data_em_C.o

