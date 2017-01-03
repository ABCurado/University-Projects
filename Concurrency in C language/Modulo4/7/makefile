vendor: vendor.o
	gcc -Wall -g vendor.o -o vendor -lrt -lpthread

vendor.o: vendor.c 
	gcc -c -g -Wall vendor.c -o vendor.o

client: client.o
	gcc -Wall -g client.o -o client -lrt -lpthread

client.o: client.c 
	gcc -c -g -Wall client.c -o client.o

run: vendor client clean
	./client & ./vendor

clean:
	rm -f vendor.o client.o

remove: clean
	rm -f vendor client
