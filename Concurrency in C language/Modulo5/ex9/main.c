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

#define NALUNOS 300

typedef struct Prova{

	int numeroAluno;
	int notaG1;
	int notaG2;
	int notaG3;
	int notaFinal;

} Prova;

/**
* ! in 4 times it woks but doesnt finnish
*/
pthread_mutex_t mutexAvaliacao = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t mutexLancarMedias = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t lock1 = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t lock2 = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t negativas;
pthread_cond_t positivas;
pthread_cond_t lancar_nota;
int notasLancadas=0;
int mediasContadas=0;
int negativasCount = 0;
int positivasCount = 0;
Prova provas[NALUNOS];

Prova * geraNotas(){
	struct timespec ts;
	clock_gettime(CLOCK_MONOTONIC, &ts);
	Prova * prova = (Prova *) malloc(sizeof(Prova));
	srand((time_t)ts.tv_nsec);
	prova->notaG1 =  rand()%101;
	prova->notaG2 =  rand()%101;
	prova->notaG3 =  rand()%101;
	return prova;
}

void * gera_provas(void *arg){
	int i;
	for(i = 0 ; i < NALUNOS; i++){
		Prova * prova  = geraNotas();
		prova->numeroAluno = (114000+i);
		provas[i] = *(prova);
		provas[i].notaFinal = 0;
		printf("Aluno %d Nota1:%d, Nota2:%d, Nota3:%d, NotaFinal:%d\n",provas[i].numeroAluno,provas[i].notaG1,provas[i].notaG2,provas[i].notaG3,provas[i].notaFinal);
		notasLancadas++;
		pthread_cond_signal(&lancar_nota);
		pthread_mutex_lock(&lock1);
	}

	printf("Acabou o lançamento de notas\n\n\n");
	pthread_exit(NULL);	
}

void * gera_media(void *arg){
	int num = *((int*)arg);
	pthread_mutex_lock(&mutexLancarMedias);
	while(NALUNOS > mediasContadas){
		pthread_cond_wait(&lancar_nota,&mutexLancarMedias);
		provas[mediasContadas].notaFinal = (provas[mediasContadas].notaG1+provas[mediasContadas].notaG2+provas[mediasContadas].notaG3)/3;
		if(provas[mediasContadas].notaFinal > 50){
			pthread_cond_signal(&positivas);
		}else{
			pthread_cond_signal(&negativas);
		}
		printf("%d: aluno %d, nota final%d\n",num,provas[mediasContadas].numeroAluno,provas[mediasContadas].notaFinal );
		mediasContadas++;
		pthread_mutex_unlock(&lock1);
		pthread_mutex_lock(&lock2);
		pthread_mutex_unlock(&mutexLancarMedias);
	}

	pthread_cond_broadcast(&lancar_nota);
	printf("Acabou a avaliação de notas%d\n\n\n ",num);
	pthread_exit(NULL);	
}

void * contador(void *arg){
	int num = *((int*)arg);
	if(num){
		pthread_mutex_lock(&mutexAvaliacao);
		while((positivasCount+negativasCount) <= NALUNOS){
			pthread_cond_wait(&positivas,&mutexAvaliacao);
			positivasCount++;
			printf("Positivas%d\n",positivasCount);
			pthread_mutex_unlock(&lock2);

		}
		pthread_cond_broadcast(&negativas);
		printf("Positivas acabou\n");
		pthread_mutex_unlock(&mutexAvaliacao);
	}else{
		pthread_mutex_lock(&mutexAvaliacao);
		while((positivasCount+negativasCount) <= NALUNOS){
			pthread_cond_wait(&negativas,&mutexAvaliacao);
			negativasCount++;
			printf("Negativas%d\n",negativasCount);
			pthread_mutex_unlock(&lock2);
		}
		pthread_cond_broadcast(&positivas);
		printf("Negativas acabou\n");
		pthread_mutex_unlock(&mutexAvaliacao);
	}


	pthread_exit(NULL);	
}

int main(void){
	pthread_t gera_provass;
	pthread_t gera_medias[2],contadors[2];
	int i, s;
	int args[2];
	
	if(pthread_cond_init(&lancar_nota,NULL) < 0){
		perror("Error creating the cond\n");
		exit(-1);
	}
	if(pthread_cond_init(&positivas,NULL) < 0){
		perror("Error creating the cond\n");
		exit(-1);
	}
	if(pthread_cond_init(&negativas,NULL) < 0){
		perror("Error creating the cond\n");
		exit(-1);
	}

	s = pthread_create(&gera_provass,NULL,gera_provas,(void*)0);
	if(s < 0){
		perror("Error creating the thread\n");
		exit(-1);
	}
	printf("\n\n\n");
	for(i = 0; i < 2; i++){
		printf("Creating thread %d and %d...\n", i+1, i+4);
		args[i] = i;
		s = pthread_create(&gera_medias[i],NULL,gera_media,(void*)&args[i]);
		if(s < 0){
			perror("Error creating the thread\n");
			exit(-1);
		}
	}
	for(i = 0; i < 2; i++){
		s = pthread_create(&contadors[i], NULL, contador, (void*)&args[i]);
		if(s < 0){
			perror("Error creating the thread\n");
			exit(-1);
		}
	}
	
	if(pthread_join(gera_provass, NULL)){
		perror("Error joining the thread\n");
		exit(-1);
	}
	for(i = 0; i < 2; i++){
		if(pthread_join(gera_medias[i], NULL)){
			perror("Error joining the thread\n");
			exit(-1);
		}
		if(pthread_join(contadors[i], NULL)){
			perror("Error joining the thread\n");
			exit(-1);
		}
	}
	printf("Positivas %d,Negativas %d",positivasCount,negativasCount);
	return 0;
}
