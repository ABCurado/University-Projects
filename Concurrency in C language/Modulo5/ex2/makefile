P1=main

all: $(P1)

$(P1): $(P1).c
	$(CC) -o $(P1) $(P1).c -lpthread

$(P2): $(P2).c
	$(CC) -o $(P2) $(P2).c

run: all
	./$(P1)

clean:
	rm -f $(P1) $(P2)

