#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include <sys/wait.h>
#include <time.h>
#include <errno.h>
#include <string.h>
#include <fcntl.h>
#include <semaphore.h>
#include <pthread.h>
#include <limits.h>

#define NHIPERMERCADOS 3
#define NPRODUTOS 5

typedef struct Hipermercado{

	int id_h;
	int id_p;
	int x;

} Hipermercado;

//pthread_mutex_t mux1;
pthread_mutex_t mux[NHIPERMERCADOS];
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t cond_hiper;
int precoMaisBaixo = INT_MAX;
int terminados = 0;
int size[NPRODUTOS];
Hipermercado produtos[NPRODUTOS*NHIPERMERCADOS];
Hipermercado vec[NHIPERMERCADOS][NPRODUTOS];

void * t1_t3(void *arg){
	int num = *((int*)arg);	
	int i;
	int idAtual;
	for(i = num*NPRODUTOS ; i < (num+1)*NPRODUTOS; i++){
		idAtual=produtos[i].id_h;
		pthread_mutex_lock(&mux[idAtual]);	
		vec[idAtual][size[idAtual]] = produtos[i];
		size[idAtual]++;
		pthread_mutex_unlock(&mux[idAtual]);
		printf("%d:Hiper: %d, Id prod: %d,Vec: %d\n ",num+1,produtos[i].id_h,produtos[i].id_p,produtos[i].x);	
	}
	
	pthread_mutex_lock(&mutex);
	terminados++;

	pthread_cond_broadcast(&cond_hiper);

	pthread_mutex_unlock(&mutex);
	printf("Thread %d acabou em %d lugar\n",num+1,terminados);
	pthread_exit(NULL);	

}

void * t4_t6(void *arg){
	int num = *((int*)arg);
	int i,sum =0;
	printf("Wating cond\n");
	pthread_mutex_lock(&mutex);
	while(terminados != NHIPERMERCADOS){
		pthread_cond_wait(&cond_hiper,&mutex);
	}
	pthread_mutex_unlock(&mutex);
	printf("Left cond\n");
	for(i = 0; i < NPRODUTOS; i++){
		printf("%d:Hiper: %d, Id prod: %d,Vec: %d\n",num+4,vec[num][i].id_h,vec[num][i].id_p,vec[num][i].x);
		sum +=vec[num][i].x;
	}

	pthread_mutex_lock(&mutex);	
	if(sum < precoMaisBaixo){
		precoMaisBaixo = sum ;
	}
	pthread_mutex_unlock(&mutex);
	
	printf("Thread %d acabou com sum %d\n",num+4,sum);
	
	pthread_exit(NULL);	
}


int main(void){
	pthread_t t1t3[NHIPERMERCADOS], t4t6[NHIPERMERCADOS];
	int i, s;
	int args[NHIPERMERCADOS];
	srand((unsigned) getpid());
	pthread_cond_init(&cond_hiper,NULL);
	for(i = 0; i < NPRODUTOS*NHIPERMERCADOS; i+=3){
		produtos[i].id_h = 0;
		produtos[i].id_p = i;
		produtos[i].x = rand()%20 ;
		produtos[i+1].id_h = 1;
		produtos[i+1].id_p = i;
		produtos[i+1].x = rand()%20;
		produtos[i+2].id_h = 2;
		produtos[i+2].id_p = i;
		produtos[i+2].x = rand()%20;
		printf("Hiper: %d, Id prod: %d,Vec: %d\n ",produtos[i].id_h,produtos[i].id_p,produtos[i].x);
		printf("Hiper: %d, Id prod: %d,Vec: %d\n ",produtos[i+1].id_h,produtos[i+1].id_p,produtos[i+1].x);
		printf("Hiper: %d, Id prod: %d,Vec: %d\n ",produtos[i+2].id_h,produtos[i+2].id_p,produtos[i+2].x);

	}
	printf("\n\n\n");
	for(i = 0; i < NHIPERMERCADOS; i++){
		printf("Creating thread %d and %d...\n", i+1, i+4);
		args[i] = i;

		s = pthread_create(&t1t3[i],NULL,t1_t3,(void*)&args[i]);
		if(s < 0){
			perror("Error creating the thread\n");
			exit(-1);
		}

		s = pthread_create(&t4t6[i], NULL, t4_t6, (void*)&args[i]);
		if(s < 0){
			perror("Error creating the thread\n");
			exit(-1);
		}
	}
	
	for(i = 0; i < NHIPERMERCADOS; i++){
		if(pthread_join(t1t3[i], NULL)){
			perror("Error joining the thread\n");
			exit(-1);
		}
		if(pthread_join(t4t6[i], NULL)){
			perror("Error joining the thread\n");
			exit(-1);
		}
	}
	printf("O numero minimo foi %d\n",precoMaisBaixo );
	return 0;
}
