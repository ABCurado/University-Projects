ex13:  activa_esquerda_direita.o activa_esquerda_direita_em_C.o main.o
	gcc -Wall -g  activa_esquerda_direita.o activa_esquerda_direita_em_C.o main.o -o ex13

main.o: main.c  activa_esquerda_direita.h
	gcc -c -g -Wall main.c -o main.o

activa_esquerda_direita.o:  activa_esquerda_direita.s
	gcc -c -g -Wall  activa_esquerda_direita.s -o  activa_esquerda_direita.o

activa_esquerda_direita_em_C.o:  activa_esquerda_direita_em_C.c
	gcc -c -g -Wall  activa_esquerda_direita_em_C.c -o  activa_esquerda_direita_em_C.o

