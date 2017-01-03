writer: writer.o
	gcc -Wall -g writer.o -o writer -lrt

reader: reader.o
	gcc -Wall -g reader.o -o reader -lrt

writer.o: writer.c 
	gcc -c -g -Wall writer.c -o writer.o

reader.o: reader.c 
	gcc -c -g -Wall reader.c -o reader.o

clean:
	rm -f reader.o
	
build: writer reader