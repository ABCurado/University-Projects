#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

int dados[1000];
int resultados[1000];
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t condicao;
int atual;

void* thread_func(void *arg){
	int i;
	int ordem = *((int*)arg);	

	for(i = ordem*200; i < (ordem+1)*200; i++){
		resultados[i] = dados[i]*2+10;
	}

	pthread_mutex_lock(&mutex);    
	while(ordem != atual)        
		pthread_cond_wait(&condicao,&mutex);  


	for(i = ordem*200; i < (ordem+1)*200; i++){
		printf("Pos: %d, valor = %d\n",i,resultados[i]);
	}
	atual++;

	pthread_cond_broadcast(&condicao);
	pthread_mutex_unlock(&mutex);
	pthread_exit((void*)NULL);
}


int main(){
	pthread_t threads[5];
	int i,args[5];
	atual=0;
	pthread_cond_init(&condicao,NULL);
	for(i = 0; i < 1000; i++){
		dados[i] = i; 
	}

    //preenche os dados da estrutura que vao ser passados por parametro cria a thread
	for(i=0;i<5;i++){
		args[i] = i;
		pthread_create(&threads[i],NULL,thread_func,(void*)&args[i]);
	}

	for(i=0;i<5;i++){
		pthread_join(threads[i],NULL);
	}

	pthread_mutex_destroy(&mutex); 
	return 0;
}
