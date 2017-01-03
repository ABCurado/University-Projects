#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "features.h"

#define NODES 16
#define SNAIL_NUMBER 3

typedef struct {
    int number;
    int * trail;
    int currentPos;
    int maxMovements;
}Snail;

int timeMatrix[NODES][NODES];
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;

void createTimerMatrix(int matrix[NODES][NODES]) {
    int i;
    for (i = 0; i < NODES; ++i) {
        int j;
        for (j = 0; j < NODES; ++j) {
            matrix[i][j] = -1;
        }
    }

    matrix[0][1] = 5;
    matrix[0][2] = 5;
    matrix[0][3] = 5;
    matrix[1][4] = 4;
    matrix[2][4] = 4;
    matrix[3][4] = 4;
    matrix[4][5] = 3;
    matrix[5][6] = 5;
    matrix[5][7] = 5;
    matrix[6][8] = 6;
    matrix[7][8] = 6;
    matrix[7][9] = 4;
    matrix[8][9] = 4;
    matrix[9][10] = 5;
    matrix[9][13] = 5;
    matrix[10][11] = 5;
    matrix[10][12] = 5;
    matrix[11][15] = 3; // ASK FOR THIS
    matrix[12][15] = 3; // ASK FOR THIS
    matrix[13][14] = 5;
    matrix[14][15] = 3;
}

Snail createSnail1() {
    Snail snail;
    snail.number = 1;
    snail.maxMovements = 10;
    snail.currentPos = 0;
    snail.trail = createDynamicArray(snail.maxMovements, sizeof(int));
    snail.trail[0]=0;
    snail.trail[1]=1;
    snail.trail[2]=4;
    snail.trail[3]=5;
    snail.trail[4]=6;
    snail.trail[5]=8;
    snail.trail[6]=9;
    snail.trail[7]=10;
    snail.trail[8]=11;
    snail.trail[9]=15;
    return snail;
}

Snail createSnail2() {
    Snail snail;
    snail.number = 2;
    snail.maxMovements = 10;
    snail.currentPos = 0;
    snail.trail = createDynamicArray(snail.maxMovements, sizeof(int));
    snail.trail[0]=0;
    snail.trail[1]=2;
    snail.trail[2]=4;
    snail.trail[3]=5;
    snail.trail[4]=7;
    snail.trail[5]=8;
    snail.trail[6]=9;
    snail.trail[7]=13;
    snail.trail[8]=14;
    snail.trail[9]=15;
    return snail;
}

Snail createSnail3() {
    Snail snail;
    snail.number = 3;
    snail.maxMovements = 9;
    snail.currentPos = 0;
    snail.trail = createDynamicArray(snail.maxMovements, sizeof(int));
    snail.trail[0]=0;
    snail.trail[1]=3;
    snail.trail[2]=4;
    snail.trail[3]=5;
    snail.trail[4]=7;
    snail.trail[5]=9;
    snail.trail[6]=10;
    snail.trail[7]=12;
    snail.trail[8]=15;
    return snail;
}

void * threadFunc(void *arg){
    Snail * snail = (Snail*)arg;
    time_t begin, end;
    begin = time(NULL);

    int i;
    for (i = 0; i < snail->maxMovements-1; ++i) {
        int currentPos = snail->trail[snail->currentPos];
        snail->currentPos+=1;
        int nextPos = snail->trail[snail->currentPos];
        pthread_mutex_lock(&mutex);
        int sleepTime = timeMatrix[currentPos][nextPos];
        timeMatrix[currentPos][nextPos]*=2;
        pthread_mutex_unlock(&mutex);
        sleep(sleepTime);
        printf("Snail %d: Took %d seconds from %d to %d.\n", snail->number, sleepTime, currentPos, nextPos);
    }

    end = time(NULL);
    unsigned int timeDiff = (unsigned)difftime(end,begin);
    printf("Snail %d: I took %u sec to finish.\n", snail->number, timeDiff);
    
    pthread_exit((void*)&timeDiff);
}

int main(char *arg, char **args){
    pthread_t snailThread[SNAIL_NUMBER];
    createTimerMatrix(timeMatrix);

    Snail snailInfo[SNAIL_NUMBER];
    snailInfo[0] = createSnail1();
    snailInfo[1] = createSnail2();
    snailInfo[2] = createSnail3();

    int i;
    for (i = 0; i < SNAIL_NUMBER; ++i) {
        pthread_create(&snailThread[i],NULL,threadFunc,(void*)&snailInfo[i]);
    }

    int ** timer = (int**)createDynamicMatrix(SNAIL_NUMBER,2,sizeof(int));
    for(i = 0; i < SNAIL_NUMBER; i++) {
        void *ret;
        pthread_join(snailThread[i], (void*)&ret);
        timer[i][0] = i+1;
        timer[i][1] = *(int*)ret;
    }

    for (i = 0; i < SNAIL_NUMBER; ++i) {
        arraySort(timer, SNAIL_NUMBER, 2);
    }

    for (i = 0; i < SNAIL_NUMBER; ++i) {
        printf("The %dº snail to finish was Snail %d.\n", i+1, timer[i][0]);
    }

    pthread_mutex_destroy(&mutex);
       
    return 0;
}
