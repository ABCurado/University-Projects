leitor: leitor.o
	gcc -Wall -g leitor.o -o leitor -lrt -lpthread

leitor.o: leitor.c 
	gcc -c -g -Wall leitor.c -o leitor.o

escritor: escritor.o
	gcc -Wall -g escritor.o -o escritor -lrt -lpthread

escritor.o: escritor.c 
	gcc -c -g -Wall escritor.c -o escritor.o

run: leitor escritor clean
	 echo "Execute ./leitor & ./escritor"

clean:
	rm -f leitor.o escritor.o

remove: clean
	rm -f leitor escritor
