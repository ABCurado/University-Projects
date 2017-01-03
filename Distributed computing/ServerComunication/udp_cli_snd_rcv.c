#include <arpa/inet.h>
#include <dirent.h>
#include <netinet/in.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <strings.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <unistd.h>

#include "configs.h"
#include "CJson/cJSON.h"
#include "CJson/cJSON_Utils.h"

cJSON * createJSON() {
	return cJSON_CreateObject();
}

cJSON * createJSONServerInfo(char *serverName) {
	cJSON *root = createJSON();
	cJSON *server;
	cJSON_AddItemToObject(root, "server", server = createJSON());
	cJSON_AddStringToObject(server, "name", serverName);
	return root;
}

cJSON * createJSONFileInfo(File file) {
	cJSON *root = createJSON();
	cJSON *files;
	cJSON_AddItemToObject(root, "file", files = createJSON());
	cJSON_AddStringToObject(files, "name", file.name);
	cJSON_AddStringToObject(files, "path", file.path);
	return root;
}

char * printJSON(cJSON *root) {
	return cJSON_Print(root);
}

char * deleteJSON(cJSON *root) {
	cJSON_Delete(root);
}

void getMyFiles(char* path, File *files, int * filesNum) {
	DIR *dir;
	struct dirent *ent;
	if ((dir = opendir (path)) != NULL) {
		/* print all the files and directories within directory */
		while ((ent = readdir (dir)) != NULL) {
			if(ent->d_type != DT_DIR) {
				char name[512];
				char filePath[512];
				strcpy(name, ent->d_name);
				strcpy(files[*filesNum].name, name);

				strcpy(filePath, path);
				strcat(filePath, "/");
				strcat(filePath, ent->d_name);
				strcpy(files[*filesNum].path, filePath);
				//printf ("ID: %d\nName: %s\nPath: %s\nMemPos: %p\n", *filesNum, files[*filesNum].name, files[*filesNum].path, &files[*filesNum]);
				//printf("ID: %d --> %p\nName: %s\n", *filesNum, &files[*filesNum], files[*filesNum].name);
				//printf("FilesNum: %d\n", *filesNum);
				*filesNum += 1;
			}
		}
		closedir (dir);
	} else {
		/* could not open directory */
		perror ("");
		exit(EXIT_FAILURE);
	}
}

void listDir(char* path, File *files, int * filesNum){
	DIR* dir;
	struct dirent *ent;
	if((dir=opendir(path)) != NULL){
		while (( ent = readdir(dir)) != NULL){
			if(ent->d_type == DT_DIR && strcmp(ent->d_name, ".") != 0 && strcmp(ent->d_name, "..") != 0){
				char tmpPath[512];
				strcpy(tmpPath, path);
				strcat(tmpPath, "/");
				strcat(tmpPath, ent->d_name);
				//printf("Entering on %s\n", tmpPath);
				getMyFiles(tmpPath,files,filesNum);
				//printf("%s\n", ent->d_name);
				listDir(ent->d_name,files,filesNum);
			}
		}
		closedir(dir);
	}
}

char ** strSplit(char * str, size_t len, int sizeSplit) {
	char **fragments;
	fragments = malloc(sizeof(*fragments) * len / sizeSplit-1);
	int i;
	for (i = 0; i < (len / sizeSplit-1); i++) {
	    fragments[i] = strndup(str + (sizeSplit-1) * i, (sizeSplit-1));
	    //printf("%s\n", fragments[i]);
	}
	return fragments;
}

int main(void)
{
	struct sockaddr_in me, target, from;
	int sock, rec, adl;
	char snd[1024], rcv[MESSAGE_MAX_LENGTH];

	sock=socket(AF_INET,SOCK_DGRAM,0);
	adl=sizeof(me);
	bzero((char *)&me,adl);
	me.sin_family=AF_INET;
	me.sin_addr.s_addr=htonl(INADDR_ANY); // endereco IP local
	me.sin_port=htons(BCAST_PORT); // porto local
	if(bind(sock,(struct sockaddr *)&me,adl)==-1) { 
		perror("Bind failed"); exit(1);
	}
	do {
		rec=recvfrom(sock,rcv,81,0,(struct sockaddr *)&from,&adl);
		printf("\nMessage Received.\n");
		rcv[rec]=0; // NULL terminate the string
		//printf("Recebido: %s\n", rcv);
		bzero((char *)&target,adl);
		target.sin_family=AF_INET;
		target.sin_addr.s_addr=inet_addr(inet_ntoa(from.sin_addr));
		target.sin_port=htons(ntohs(from.sin_port)); // porto de destino
		//printf("%d\n", strcmp(rcv, BCAST_REQUEST));
		if(strcmp(rcv, BCAST_REQUEST)==0) {
			cJSON *data;
			File files[200];
			int filesNum = 0;

			//printf("\nFILES FOUND: \n");

			getMyFiles(FILES_ROOT_FOLDER,files,&filesNum);
			listDir(FILES_ROOT_FOLDER,files,&filesNum);
			printf("Found %d files.\n",filesNum);
			//addNewObject(data, SERVER_NAME, inet_ntoa(me.sin_addr), files, filesNum);

			char hostname[1024];
			hostname[1023] = '\0';
			gethostname(hostname, 1023);

			data = createJSONServerInfo(hostname);
			sendto(sock,printJSON(data),MESSAGE_MAX_LENGTH,0,(struct sockaddr *)&target,adl);
			int i;
			for (i = 0; i < filesNum; ++i) {
				//printf("ID: %d --> %p\nName: %s\n", i, &files[i], files[i].name);
				//printf("Name: %s\nPath: %s\n", files[i].name, files[i].FILES_ROOT_FOLDER);
				data = createJSONFileInfo(files[i]);
				sendto(sock,printJSON(data),MESSAGE_MAX_LENGTH,0,(struct sockaddr *)&target,adl);
			}
			printf("Data sent.\n");
		}
	} while(strcmp(rcv,"sair")!=0);
	sendto(sock,rcv,strlen(rcv),0,(struct sockaddr *)&target,adl);
	close(sock);
	return 0;
}