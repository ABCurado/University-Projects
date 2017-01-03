#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

int vec[1000];
int saldo; 
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;

void* thread_func(void *arg){
    int i;
    int num = *((int*)arg);	
    int total = 0;
    for(i = num*200; i < (num+1)*200; i++){
        total += vec[i];
    }
    printf("A thread %d calculou : %d\n",num,total);
    pthread_mutex_lock(&mutex);
    saldo += total;
    pthread_mutex_unlock(&mutex);
    pthread_exit((void*)NULL);
}


int main(){
    pthread_t threads[5];
    int i,totalCalculadoSemThreads = 0,args[5];;
    for(i = 0; i < 1000; i++){
        vec[i] = i;
        totalCalculadoSemThreads +=vec[i]; 
    }
    printf("O total Calculado Sem Threads é %d \n",totalCalculadoSemThreads);
    //preenche os dados da estrutura que vao ser passados por parametro cria a thread
    for(i=0;i<5;i++){
         args[i] = i;
        pthread_create(&threads[i],NULL,thread_func,(void*)&args[i]);
    }
  
    for(i=0;i<5;i++){
        pthread_join(threads[i],NULL);
	}
    printf("O Saldo total é %d \n",saldo);
    pthread_mutex_destroy(&mutex); 
    return 0;
}
