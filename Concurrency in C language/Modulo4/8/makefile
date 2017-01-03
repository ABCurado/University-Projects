levanta: levanta.o
	gcc -Wall -g levanta.o -o levanta -lrt -lpthread

levanta.o: levanta.c 
	gcc -c -g -Wall levanta.c -o levanta.o

consulta: consulta.o
	gcc -Wall -g consulta.o -o consulta -lrt -lpthread

consulta.o: consulta.c 
	gcc -c -g -Wall consulta.c -o consulta.o

servidor: servidor.o
	gcc -Wall -g servidor.o -o servidor -lrt -lpthread

servidor.o: servidor.c 
	gcc -c -g -Wall servidor.c -o servidor.o

run: levanta consulta servidor clean
	./levanta & ./consulta & ./servidor

clean:
	rm -f levanta.o consulta.o servidor.o

remove: clean
	rm -f levanta consulta servidor
