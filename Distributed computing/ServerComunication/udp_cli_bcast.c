#include <strings.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <sys/mman.h>
#include <fcntl.h>
#include "configs.h"
#include "CJson/cJSON.h"
#include "CJson/cJSON_Utils.h"

#define JSON_FILE_PATH "../filesystem.json"
#define CFG_FILE_PATH "./servers.cfg"
#define SHM_SERVER_NAME "/shm_server"
#define SHM_SERVER_NUM_NAME "/shm_server_num"
#define IP_STR_MAX_SIZE 16

typedef struct {
	char serverName[200];
	char serverIP[16];
	File files[200];
	int filesNum;
}Server;

char ** readConfigFile(int * serverCount) {
	FILE *fp;
	char ** servers = (char **)malloc((*serverCount+1)*sizeof(char*));
	char * line = NULL;
    size_t len = 0;
    ssize_t read;

	fp = fopen(CFG_FILE_PATH,"r"); // read mode
	if( fp == NULL )
	{
		perror("Error while opening the file.\n");
		exit(EXIT_FAILURE);
	}

	while ((read = getline(&line, &len, fp)) != -1) {
		servers = (char **)realloc(servers, (*serverCount+1)*sizeof(char*));
        //printf("Retrieved line of length %zu :\n", read);
        // Remove \n char
        if(strcmp(line,"")!=0) {
	        char *ip = strtok(line,"\n");
	        servers[*serverCount] = (char *)malloc(IP_STR_MAX_SIZE);
	        strcpy(servers[*serverCount],ip);
	        *serverCount += 1;
	    }
    }

    fclose(fp);
    if (line){
        free(line);
    }

    return servers;
}

void sendRefreshEventToHttpService() {
	struct sockaddr_in me,target;
	int sock, adl;

	printf("Sending Refresh Event to HTTP Service\n");

	sock=socket(AF_INET,SOCK_DGRAM,0);
	adl=sizeof(me);
	bzero((char *)&me,adl);
	me.sin_family=AF_INET;
	me.sin_addr.s_addr=inet_addr("127.0.0.1"); // endereco IP local
	me.sin_port=htons(-1); // porto local
	if(bind(sock,(struct sockaddr *)&me,adl)==-1) { 
		perror("Bind failed"); exit(EXIT_FAILURE);
	}
	adl=sizeof(target);
	bzero((char *)&target,adl);
	target.sin_family=AF_INET;
	target.sin_addr.s_addr=inet_addr("127.0.0.1");
	target.sin_port=htons(EVENT_TO_HTTP_PORT); // porto de destino
	sendto(sock,EVENT_TO_HTTP_MESSAGE,strlen(EVENT_TO_HTTP_MESSAGE),0,(struct sockaddr *)&target,adl);
	close(sock);
}

cJSON * createServersJson(Server * servers, int serversNum) {
	cJSON *root = cJSON_CreateArray();
	cJSON *srvArr;
	int i;
	for (i = 0; i < serversNum; ++i) {
		cJSON *srv;
		cJSON *allFiles;
		cJSON_AddItemToArray(root, srv = cJSON_CreateObject());
		//cJSON_AddItemToObject(srv, "name", cJSON_CreateString("Jack (\"Bee\") Nimble"));
		//cJSON_AddStringToObject(srv, "name", name);
		cJSON_AddStringToObject(srv, "name", servers[i].serverName);
		cJSON_AddStringToObject(srv, "ip", servers[i].serverIP);
		cJSON_AddItemToObject(srv, "files", allFiles = cJSON_CreateArray());
		int j;
		for (j = 0; j < servers[i].filesNum; ++j) {
			cJSON *file;
			/*char fileNum[10];
			sprintf(fileNum, "%d", j+1);*/
			cJSON_AddItemToArray(allFiles, file = cJSON_CreateObject());
			cJSON_AddStringToObject(file, "name", servers[i].files[j].name);
			cJSON_AddStringToObject(file, "path", servers[i].files[j].path);
		}
	}
	return root;
}

void createJsonFile(Server * servers, int serversNum) {
	printf("\nCreating JSON file.\n");
	//printf("Generating JSON file to: '%s'\nPos: %p -> Value: %s\n", JSON_FILE_PATH, servers, servers[0].serverIP);
	cJSON *root = createServersJson(servers, serversNum);
	char * jsonStr = cJSON_Print(root);
	FILE *fp = fopen(JSON_FILE_PATH, "w");
	if (!fp){
    	perror("Error creating file.");
	}
	fprintf(fp, "%s", jsonStr);
	fclose(fp);
}

void clearServersInfo(Server * servers, int * serversNum){
	int i;
	for (i = 0; i < *serversNum; ++i) {
		servers[i].filesNum = 0;
	}
	*serversNum = 0;
}

int isJsonFile(char * str) {
	cJSON * root = cJSON_Parse(str);
	cJSON * child = root->child;
	if(strcmp(child->string,"file") == 0) {
		return 1;
	}
	return 0;
}

char * getServerFromJSON(char * str) {
	cJSON * root = cJSON_Parse(str);
	cJSON * serverJson = cJSON_GetObjectItem(root,"server");
	char * serverName = cJSON_GetObjectItem(serverJson,"name")->valuestring;
	return serverName;
}

File getFileFromJSON(char * str) {
	File file;
	cJSON * root = cJSON_Parse(str);
	cJSON * fileJson = cJSON_GetObjectItem(root,"file");
	char * name = cJSON_GetObjectItem(fileJson,"name")->valuestring;
	char * path = cJSON_GetObjectItem(fileJson,"path")->valuestring;
	strcpy(file.name, name);
	strcpy(file.path, path);
	return file;
}

int findServerIndex(Server * servers, int serversNum, char * ip) {
	int i;
	//printf("Servers N: %d - Finding IP: %s\n", serversNum, ip);
	for (i = 0; i < serversNum; ++i) {
		if(strcmp(servers[i].serverIP, ip) == 0) {
			return i;
		}
	}
	return -1;
}

Server * openShmServer(char* name, int size) {
    int fd;
    Server* list;

    /*cria o objeto e devolve o descritor de memoria partilhada*/
    if ((fd = shm_open(name, O_CREAT | O_RDWR, S_IRWXU | S_IRWXG)) < 0) {
        perror("Erro ao criar e abrir a memória partilhada.");
        exit(EXIT_FAILURE);
    }

    /*define o tamanho da memória partilhada*/
    if(ftruncate(fd, size)) {
    	perror("Erro no ftruncate.");
    	exit(EXIT_FAILURE);
    };

    /*mapeia a memória partilhada e retorna o apontador*/
    list = (Server *) mmap(NULL, size, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
    if (list == NULL) {
        perror("Erro ao mapear a memória partilhada.");
        exit(EXIT_FAILURE);
    }

    return list;
}

int * openShmInt(char* name, int size) {
    int fd;
    int* integer;

    /*cria o objeto e devolve o descritor de memoria partilhada*/
    if ((fd = shm_open(name, O_CREAT | O_RDWR, S_IRWXU | S_IRWXG)) < 0) {
        perror("Erro ao criar e abrir a memória partilhada.");
        exit(EXIT_FAILURE);
    }

    /*define o tamanho da memória partilhada*/
    if(ftruncate(fd, size)) {
    	perror("Erro no ftruncate.");
    	exit(EXIT_FAILURE);
    };

    /*mapeia a memória partilhada e retorna o apontador*/
    integer = (int *) mmap(NULL, size, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
    if (integer == NULL) {
        perror("Erro ao mapear a memória partilhada.");
        exit(EXIT_FAILURE);
    }

    return integer;
}

int main(int argc, char **argv) {
	Server * servers = openShmServer(SHM_SERVER_NAME, sizeof(Server)*100);
	int * serversNum = openShmInt(SHM_SERVER_NUM_NAME, sizeof(int));
	*serversNum = 0;
	struct sockaddr_in me, server;
	int sock, adl, res;
	char linha[MESSAGE_MAX_LENGTH];
	pid_t pid;
	
	sock=socket(AF_INET,SOCK_DGRAM,0);
	adl=1; setsockopt(sock,SOL_SOCKET, SO_BROADCAST, &adl, sizeof(adl));
	adl=sizeof(me);

	bzero((char *)&me,adl);
	me.sin_family=AF_INET;
	me.sin_addr.s_addr=htonl(INADDR_ANY); /* endereco IP local */
	me.sin_port=htons(0); /* porto local (0=auto assign) */
	bind(sock,(struct sockaddr *)&me,adl);

	bzero((char *)&server,adl);
	server.sin_family=AF_INET;
	server.sin_port=htons(BCAST_PORT); /* porto do servidor */
	if (pid = fork()) {
		do {
			res=recvfrom(sock,linha,MESSAGE_MAX_LENGTH,0,(struct sockaddr *)&server,&adl);

	        //linha[res]=0; /* NULL terminate the string */
	        //server.sin_addr.s_addr
			//printf("Recebido: %s\n",linha);
			char * ip = inet_ntoa(server.sin_addr);
			int serverIndex = findServerIndex(servers, *serversNum, ip);
			//printf("WTF happend !! :%d:\n",serverIndex);
			if(serverIndex < 0) {
				//printf("What ");
				serverIndex = *serversNum;
				//printf("is ");
				strcpy(servers[serverIndex].serverName, ip);
				//printf("going ");
				strcpy(servers[serverIndex].serverIP, ip);
				//printf("on");
				servers[serverIndex].filesNum = 0;
				//printf(" !!!\n");
				*serversNum+=1;
			}
			//printf("LOOOOOOOOL\n");
			//printf("Get: %s\n",linha);
			if(isJsonFile(linha)) {
				int currentFilesNum = servers[serverIndex].filesNum;
				File tmpFile = getFileFromJSON(linha);
				strcpy(servers[serverIndex].files[currentFilesNum].name, tmpFile.name);
				strcpy(servers[serverIndex].files[currentFilesNum].path, tmpFile.path);
				servers[serverIndex].filesNum++;
			} else {
				char * serverName = getServerFromJSON(linha);
				strcpy(servers[serverIndex].serverName, serverName);
			}
		}while(strcmp(linha,"sair")!=0);
		exit(0);
	}
	do {
		clearServersInfo(servers,serversNum);
		//printf("Frase a enviar (\"sair\" para terminar): ");
		//GETS(linha,MESSAGE_MAX_LENGTH);
		//strcpy(linha, BCAST_REQUEST);
		int targetCount = 0;
		char **targets = readConfigFile(&targetCount);
		targets = (char **)realloc(targets, (targetCount+1)*sizeof(char*));
		targets[targetCount] = (char *)malloc(IP_STR_MAX_SIZE);
		strcpy(targets[targetCount],"255.255.255.255");
		targetCount += 1;
		printf("\nSending Message to:\n");
		int i;
	    for (i = 0; i < targetCount; ++i) {
	    	//printf("LEL\n");
	    	printf("%d: %s\n", i+1, targets[i]);
	    	//printf("EI\n");
	    	server.sin_addr.s_addr=inet_addr(targets[i]); /* endereco de broadcast na rede local */
	    	//printf("AI\n");
	    	sendto(sock,BCAST_REQUEST,strlen(BCAST_REQUEST),0,(struct sockaddr *)&server,adl);
	    	//printf("DONG\n");
	    }
	    //printf("FDS\n");
		//printf("Message \"%s\" sent", BCAST_REQUEST);
		sleep(BCAST_ANSWERS_WAIT_TIME);
		createJsonFile(servers,*serversNum);
		sendRefreshEventToHttpService();
		sleep(BCAST_REQUEST_TIMER-BCAST_ANSWERS_WAIT_TIME);
	}while(strcmp(BCAST_REQUEST,"sair")!=0); // in seconds
	close(sock);
	/*desfaz o mapeamento da memória partilhada*/
	if (munmap(servers, sizeof(Server)) < 0) {
        perror("Erro ao desligar um objeto de memória partilhada.");
        exit(EXIT_FAILURE);
    }
    /*apaga a memória partilhada*/
    if (shm_unlink(SHM_SERVER_NAME) < 0) {
        perror("Erro ao remover a memória partilhada do sistema de ficheiros.");
        exit(EXIT_FAILURE);
    }
    /*desfaz o mapeamento da memória partilhada*/
	if (munmap(serversNum, sizeof(int)) < 0) {
        perror("Erro ao desligar um objeto de memória partilhada.");
        exit(EXIT_FAILURE);
    }
    /*apaga a memória partilhada*/
    if (shm_unlink(SHM_SERVER_NUM_NAME) < 0) {
        perror("Erro ao remover a memória partilhada do sistema de ficheiros.");
        exit(EXIT_FAILURE);
    }
	return 0;
}