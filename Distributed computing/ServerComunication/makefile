JSON_IMPORTS=CJson/cJSON.c CJson/cJSON_Utils.c
P1=udp_cli_bcast
P2=udp_cli_snd_rcv
SHM_MEM_PATH=/dev/shm/shm_server*

all: $(P1) $(P2)

$(P1): $(P1).c
	$(CC) -o $(P1).run $(P1).c $(JSON_IMPORTS) -lm -lrt

$(P2): $(P2).c
	$(CC) -o $(P2).run $(P2).c $(JSON_IMPORTS) -lm

run: all
	./$(P2).run & ./$(P1).run

remove_shm_memory:
	sudo rm $(SHM_MEM_PATH)

clean: remove_shm_memory
	rm -f $(P1).run $(P2).run

