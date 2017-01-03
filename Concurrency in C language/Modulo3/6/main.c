#include <stdio.h>
#include <sys/types.h>
#include <sys/mman.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <time.h>
#include <sys/stat.h>  
#include <fcntl.h>
#include <string.h>
#define VEC_SIZE 10

typedef struct{
	char pathToFile[100];
	char wordToSearch[50] ;
	int numOfOcorrences;
}wordSearch;

void childCode(wordSearch *wordSearchStruct){
	FILE *fp;
	int line_num = 1;
	wordSearchStruct->numOfOcorrences = 0;
	char temp[512];
	
	if((fp = fopen(wordSearchStruct->pathToFile, "r")) == NULL) {
		exit(-1);
	}

	while(fgets(temp, 512, fp) != NULL) {
		if((strstr(temp, wordSearchStruct->wordToSearch)) != NULL) {
			printf("A match found on line: %d on file :%s\n", line_num,wordSearchStruct->pathToFile);
			printf("\n%s\n", temp);
			wordSearchStruct->numOfOcorrences++;
		}
		line_num++;
	}

	if(fp) {
		fclose(fp);
	}
	exit(0);
}

void fillVec(wordSearch *wordSearchStruct){
	int i ;
	char *word = "for";
	for(i = 0; i < VEC_SIZE; i++){
		strcpy(wordSearchStruct[i].wordToSearch, word);
		sprintf(wordSearchStruct[i].pathToFile,"%s%d%s","/home/ab/Desktop/#SCOMP/scomptgg05/Modulo2/",i+1,"/main.c");
	}
}

int main(int argc, char *argv[]) {
	int i,fd, data_size = (VEC_SIZE *sizeof(wordSearch));
	wordSearch *shared_data;
	if((fd = shm_open("/shmex6", O_CREAT|O_RDWR, S_IRUSR|S_IWUSR) ) < 0){
		perror("shm_open error");
		exit(0);
	}
	if(ftruncate (fd, data_size)  < 0){
		perror("ftruncate error");
		exit(0);
	}

	shared_data = (wordSearch *)mmap(NULL, data_size,PROT_READ|PROT_WRITE,MAP_SHARED,fd,0);

	fillVec(shared_data);

	for(i = 0; i < VEC_SIZE; i++){
		if(fork()==0){
			childCode(&shared_data[i]);
		}
	}

	for(i = 0; i < VEC_SIZE; i++){
		wait(NULL);
	}

	int max = 0;
	for(i = 0; i < VEC_SIZE; i++){
		printf("O exercicio %d contem %d vezes a palavra 'for'\n",i+1,shared_data[i].numOfOcorrences);
	}

	int r = munmap(shared_data, data_size); /* desfaz mapeamento */
	if (r < 0) exit(1);
	r = shm_unlink("/shmex6"); /* remove do sistema */
	if (r < 0) exit(1);

	return 0;
}

