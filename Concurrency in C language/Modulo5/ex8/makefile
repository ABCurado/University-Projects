P1=main

all: $(P1)

$(P1):
	$(CC) -o $(P1) $(P1).c -lpthread

debug:
	$(CC) -g $(P1).c -o $(P1).o -lpthread
	gdb $(P1).o

run: all
	./$(P1)

clean_debug:
	rm -f $(P1).o

clean: clean_debug
	rm -f $(P1) 

