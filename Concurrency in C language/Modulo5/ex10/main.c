#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "features.h"

#define CLIENTS_NUM 2
#define MESSAGES_TO_SEND 3

typedef struct {
    int id;
    char * ip;
}Client;

typedef struct {
    char * message;
    int * senderId;
    int readersNum;
    int messageReadersId[CLIENTS_NUM-1];
}Message;

typedef struct {
    int messageNum;
    int serverMessagesAllowed;
    Message * message_queue;
    Message * message_queue_allowed;
}Broker;

Broker broker;
pthread_mutex_t brokerMutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t serverChannel = PTHREAD_COND_INITIALIZER;
pthread_cond_t clientReceive = PTHREAD_COND_INITIALIZER;
pthread_cond_t clientRead = PTHREAD_COND_INITIALIZER;

int hasBadWord(char * str, char ** badWords, int numBadWords) {
    int i;
    for (i = 0; i < numBadWords; ++i) {
        if(strcmp(str, badWords[i])==0) {
            return 1;
        }
    }
    return 0;
}

int messageAlreadyPrinted(Client * client, Message * msg) {
    int i;
    for (i = 0; i < msg->readersNum; ++i) {
        if(client->id == msg->messageReadersId[i]) {
            return 1;
        }
    }
    return 0;
}

void * threadClientMessageSender(void *arg){
    Client * client = (Client*)arg;

    printf("Client %d sending message.\n", client->id);
    int i;
    for (i = 0; i < MESSAGES_TO_SEND; ++i) {
        char messageStr[] = "RANDOM MESSAGE";

        //Message * message = (Message*)malloc(sizeof(Message));
        pthread_mutex_lock(&brokerMutex);
        printf("Client %d preparing message.\n", client->id);
        broker.message_queue[broker.messageNum].senderId = &client->id;
        broker.message_queue[broker.messageNum].message = messageStr;
        broker.message_queue[broker.messageNum].readersNum = 0;
        
        //broker.message_queue = (Message*)realloc(broker.message_queue, broker.messageNum+1);
        //broker.message_queue = tmp;
        //broker.message_queue[broker.messageNum] = message;
        broker.messageNum+=1;
        pthread_mutex_unlock(&brokerMutex);

        pthread_cond_signal(&serverChannel);
    }
    printf("Client %d sending message end.\n", client->id);
    pthread_exit((void*)NULL);
}

void * threadClientReceive(void *arg){
    Client * client = (Client*)arg;
    char * message;
    printf("Client %d Receiving\n", client->id);
    do {
        while(broker.serverMessagesAllowed == 0) {
            pthread_cond_wait(&clientReceive, &brokerMutex);
        }

        // If the message is not from this client
        if(*(broker.message_queue_allowed[0].senderId)!=client->id) {
            message = broker.message_queue_allowed[0].message;
            if(messageAlreadyPrinted(client, &broker.message_queue_allowed[0])==0) {
                pthread_mutex_lock(&brokerMutex);
                printf("ID %d:  %s (message from %d)\n", client->id, broker.message_queue_allowed[0].message, *broker.message_queue_allowed[0].senderId);
                int readers = broker.message_queue_allowed[0].readersNum;
                broker.message_queue_allowed[0].messageReadersId[readers] = client->id;
                broker.message_queue_allowed[0].readersNum+=1;
                pthread_mutex_unlock(&brokerMutex);
            }
        }
        pthread_cond_signal(&clientRead);
    }while(strcmp(message, "exit")!=0);
    printf("Client %d Receiving End\n", client->id);
    pthread_exit((void*)NULL);
}

void * threadServerMessageRemover(void *arg){
    printf("Message Remover\n");
    while (0 < (CLIENTS_NUM * MESSAGES_TO_SEND)) {
        printf("Checking messages to remove\n");
        while(broker.serverMessagesAllowed == 0 || broker.message_queue_allowed[0].readersNum != CLIENTS_NUM - 1) {
            pthread_cond_wait(&clientRead, &brokerMutex);
        }
        printf("Removing message\n");
        int messageNum = broker.serverMessagesAllowed;
        int i;
        for (i = 0; i < messageNum; ++i) {
            if(broker.message_queue_allowed[0].readersNum == CLIENTS_NUM - 1) {
                broker.message_queue_allowed = &broker.message_queue_allowed[1];
                broker.serverMessagesAllowed -= 1;
            }
            
        }
    }
    printf("Message Remover End\n");
}

void * threadServerMessagesVerifier(void *arg){
    int numBadWords = 2;
    int badWordsMaxChars = 50;
    char ** badWords = (char**)createDynamicMatrix(numBadWords, badWordsMaxChars, sizeof(char));
    badWords[0] = "Fuck";
    badWords[1] = "Shit";

    int messagesRead = 0;
    while (messagesRead < (CLIENTS_NUM * MESSAGES_TO_SEND)) {
        printf("Going to verify\n");
        while(broker.messageNum == 0) {
            printf("Waiting\n");
            //pthread_mutex_unlock(&brokerMutex);
            pthread_cond_wait(&serverChannel, &brokerMutex);
            //pthread_mutex_lock(&brokerMutex);
        }
        pthread_mutex_lock(&brokerMutex);
        printf("Sending to queue\n");
        int messageNum = broker.messageNum;
        int i;
        for (i = 0; i < messageNum; ++i) {
            messagesRead++;
            if(hasBadWord(broker.message_queue[0].message, badWords, numBadWords)==0) {
                //broker.message_queue_allowed = (Message*)realloc(broker.message_queue_allowed, broker.serverMessagesAllowed+1);
                broker.message_queue_allowed[broker.serverMessagesAllowed] = broker.message_queue[0];
                broker.serverMessagesAllowed += 1;
                printf("Message sent\n");
            } else {printf("Message not allowed\n");}
            broker.message_queue = &broker.message_queue[1];
            broker.messageNum -= 1;
        }
        pthread_mutex_unlock(&brokerMutex);
        pthread_cond_broadcast(&clientReceive);
    }
    printf("Finish verify\n");
}

int main(char *arg, char **args){
    pthread_t clientsSendThread[CLIENTS_NUM];
    pthread_t clientsReceiveThread[CLIENTS_NUM];
    pthread_t serverRestrictThread;
    pthread_t serverUpdateThread;
    Client clients[CLIENTS_NUM];

    broker.messageNum = 0;
    broker.serverMessagesAllowed = 0;
    broker.message_queue = (Message*)createDynamicArray(CLIENTS_NUM*MESSAGES_TO_SEND+1, sizeof(Message));
    broker.message_queue_allowed = (Message*)createDynamicArray(CLIENTS_NUM*MESSAGES_TO_SEND+1, sizeof(Message));

    pthread_create(&serverRestrictThread,NULL,threadServerMessagesVerifier,NULL);
    pthread_create(&serverUpdateThread,NULL,threadServerMessageRemover,NULL);
    
    int i;
    for (i = 0; i < CLIENTS_NUM; ++i) {
        clients[i].id = i+1;
        char * ip = createDynamicArray(16, sizeof(char));
        strcpy(ip, "192.168.1.1");
        clients[i].ip = ip;
        pthread_create(&clientsSendThread[i],NULL,threadClientMessageSender,(void*)&clients[i]);
        pthread_create(&clientsReceiveThread[i],NULL,threadClientReceive,(void*)&clients[i]);
    }

    for(i = 0; i < CLIENTS_NUM; i++) {
        pthread_join(clientsSendThread[i], NULL);
        pthread_join(clientsReceiveThread[i], NULL);
	}
    
    pthread_join(serverRestrictThread, NULL);
    pthread_join(serverUpdateThread, NULL);

    pthread_mutex_destroy(&brokerMutex);
    pthread_cond_destroy(&serverChannel);
    pthread_cond_destroy(&clientReceive);

    //free(broker.message_queue);
    //free(broker.message_queue_allowed);
       
    return 0;
}
