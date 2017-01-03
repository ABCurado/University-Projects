#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include "features.h"

#define DB_KEYS_NUMBER 10000
#define NUMBER_PER_KEY 5
#define MIN_KEY_VALUE 1
#define MAX_KEY_VALUE 50
#define THREAD_NUM 10

int ** keys;
int keyCount[MAX_KEY_VALUE-MIN_KEY_VALUE+1];
// For debug purpose
//int manKeyCount[MAX_KEY_VALUE-MIN_KEY_VALUE+1];
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;

void * threadFunc(void *arg){
    int * threadNum = (int*)arg;
    int searchNum = DB_KEYS_NUMBER/THREAD_NUM;
    
    int i;
    for (i = (*threadNum*searchNum); i < ((*threadNum*searchNum)+searchNum); ++i) {
        int j;
        for (j = 0; j < NUMBER_PER_KEY; ++j){
            pthread_mutex_lock(&mutex);
            keyCount[(keys[i][j])-1] += 1;
            pthread_mutex_unlock(&mutex);
            // For debug purpose
            //printf("%d: Já tem %d\n", keys[i][j], keyCount[(keys[i][j])-1]);
            //sleep(5);
        }
    }
    
    pthread_exit((void*)NULL);
}

int main(char *arg, char **args){
    keys = generateMultiDimentionalArray(DB_KEYS_NUMBER, NUMBER_PER_KEY, MIN_KEY_VALUE, MAX_KEY_VALUE); 
    srand(time(NULL));
    int i;
    for (i = 0; i < MAX_KEY_VALUE-MIN_KEY_VALUE+1; ++i) {
        keyCount[i] = 0;
        // For debug purpose
        //manKeyCount[i] = 0;
    }

    pthread_t threads[THREAD_NUM];
    int threadNum[THREAD_NUM];

    for(i = 0; i < THREAD_NUM; i++) {
        threadNum[i] = i;
        pthread_create(&threads[i],NULL,threadFunc,(void*)&threadNum[i]);
    }
  
    for(i = 0; i < THREAD_NUM; i++) {
        void *ret;
        pthread_join(threads[i], NULL);
	}

    // For debug purpose
    /*for (i = 0; i < DB_KEYS_NUMBER; ++i) {
        int j;
        for (j = 0; j < NUMBER_PER_KEY; ++j){
            manKeyCount[(keys[i][j])-1] += 1;
            // For debug purpose
            //printf("%d: Já tem %d\n", keys[i][j], keyCount[(keys[i][j])-1]);
            //sleep(5);
        }
    }*/

    for (i = 0; i < MAX_KEY_VALUE-MIN_KEY_VALUE+1; ++i){
        printf("O número %d foi encontrado %d vezes.\n", i+1, keyCount[i]);
        // For debug purpose
        //printf("O número contado à mão foi: %d.\n", manKeyCount[i]);
    }
       
    return 0;
}
