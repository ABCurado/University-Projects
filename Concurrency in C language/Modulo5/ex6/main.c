#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include "features.h"

// Not order for easy random implementation
#define CITY_B 1
#define CITY_A 2
#define CITY_C 3
#define CITY_D 4
#define TRAIN_NUMBER 20

pthread_mutex_t lineAB;
pthread_mutex_t lineBC;
pthread_mutex_t lineBD;

typedef struct {
    int num;
    int from;
    int to;
}Train;

char * hora() {
    return getCurrentTime();
}

pthread_mutex_t * nextLineMutex(int from, int to) {
    switch(from) {
        case CITY_A: {
            return &lineAB;
        }
        case CITY_B: {
            switch(to) {
                case CITY_A: {
                    return &lineAB;
                }
                case CITY_C: {
                    return &lineBC;
                }
                case CITY_D: {
                    return &lineBD;
                }
            }
            break;
        }
        case CITY_C: {
            return &lineBC;
        }
        case CITY_D: {
            return &lineBD;
        }
    }
}

char * getCityName(int num) {
    switch(num) {
        case CITY_A: {
            return "Cidade A";
        }
        case CITY_B: {
            return "Cidade B";
        }
        case CITY_C: {
            return "Cidade C";
        }
        case CITY_D: {
            return "Cidade D";
        }
    }
    return "No city";
}

char * getLineName(int from, int to) {
    switch(from) {
        case CITY_A: {
            return "cidadeA-cidadeB";
            break;
        }
        case CITY_B: {
            switch(to) {
                case CITY_A: {
                    return "cidadeA-cidadeB";
                    break;
                }
                case CITY_C: {
                    return "cidadeB-cidadeC";
                    break;
                }
                case CITY_D: {
                    return "cidadeB-cidadeD";
                    break;
                }
            }
            break;
        }
        case CITY_C: {
            return "cidadeB-cidadeC";
            break;
        }
        case CITY_D: {
            return "cidadeB-cidadeD";
            break;
        }
    }
    return "No line";
}

void * threadFunc(void *arg){
    Train * train = (Train*)arg;
    char * partida = hora();
    char * chegada;

    //printf("Train %d, From: %s, To: %s\n", train->num, getCityName(train->from), getCityName(train->to));
    
    pthread_mutex_t * myMutex = nextLineMutex(train->from, CITY_B);
    pthread_mutex_lock(myMutex);
    printf("Train: %d\tLine: %s\tFrom: %s\tTo: %s\n", train->num, 
        getLineName(train->from, CITY_B), getCityName(train->from), getCityName(train->to));
    // Each line takes x sec to finish
    sleep(3);
    pthread_mutex_unlock(myMutex);

    myMutex = nextLineMutex(CITY_B, train->to);
    pthread_mutex_lock(myMutex);
    printf("Train: %d\tLine: %s\tFrom: %s\tTo: %s\n", train->num, 
        getLineName(CITY_B, train->to), getCityName(train->from), getCityName(train->to));
    // Each line takes x sec to finish
    sleep(3);
    pthread_mutex_unlock(myMutex);

    chegada = hora();
    printf("\nTrain: %d\nDeparture: %s\tArrival: %s\n\n", train->num, partida, chegada);

    pthread_exit((void*)NULL);
}

int main(char *arg, char **args){
    pthread_t threads[TRAIN_NUMBER];
    Train trains[TRAIN_NUMBER];

    lineAB = (pthread_mutex_t)PTHREAD_MUTEX_INITIALIZER;
    lineBC = (pthread_mutex_t)PTHREAD_MUTEX_INITIALIZER;
    lineBD = (pthread_mutex_t)PTHREAD_MUTEX_INITIALIZER;
    
    srand(time(NULL));
    int i;
    for (i = 0; i < TRAIN_NUMBER; ++i) {
        trains[i].num = i+1;
        trains[i].from = generateRandom(CITY_A, CITY_D, i);
        if(trains[i].from == CITY_A) {
            trains[i].to = generateRandom(CITY_C, CITY_D, i);
        } else {
            trains[i].to = CITY_A;
        }
        pthread_create(&threads[i],NULL,threadFunc,(void*)&trains[i]);
    }

    for(i = 0; i < TRAIN_NUMBER; i++) {
        pthread_join(threads[i], NULL);
	}

    pthread_mutex_destroy(&lineAB);
    pthread_mutex_destroy(&lineBC);
    pthread_mutex_destroy(&lineBD);
       
    return 0;
}
