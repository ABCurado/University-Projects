#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include "features.h"

#define ARRAY_POSITIONS 1000
#define THREAD_NUM 5

int * array;

void * threadFunc(void *arg){
    int * args = (int*)arg;
    int * threadNum = &args[0];
    int * value = &args[1];
    int searchNum = ARRAY_POSITIONS/THREAD_NUM;
    //printf("Thread: %d\nValue to find: %d\nFrom: %d -- To: %d\n\n",*threadNum,*value,(*threadNum*searchNum),((*threadNum*searchNum)+searchNum));
    int i;
    for (i = (*threadNum*searchNum); i < ((*threadNum*searchNum)+searchNum); ++i) {
        if(*value == array[i]) {
            printf("Thread %d: I found the value on position %d.\n", *threadNum, i);
            //int * thread = (int*)malloc(sizeof(int)*1);
            //*thread = *threadNum;
            pthread_exit((void*)threadNum);
        }
    }
    
    pthread_exit((void*)NULL);
}

int main(char *arg, char **args){
    pthread_t threads[THREAD_NUM];
    int values[THREAD_NUM][2];
    array = generateArrayDifferentValues(ARRAY_POSITIONS);
    int valueToFind = generateRandom(1, ARRAY_POSITIONS, 0);
    int i;
    for(i = 0; i < THREAD_NUM; i++) {
        values[i][0] = i;
        values[i][1] = valueToFind;
        pthread_create(&threads[i],NULL,threadFunc,(void*)values[i]);
    }
  
    for(i = 0; i < THREAD_NUM; i++) {
        void *ret;
        pthread_join(threads[i], (void*)&ret);
        if(ret != NULL) {
            int * threadNum = (int*)ret;
            printf("Main: Thread %d found the value\n", *threadNum);
        }
	}
       
    return 0;
}
