#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

typedef struct{
    int numero;
    char morada[10];
    char nome[10];
    int pos;
}dados;

void* thread_func(void *arg){
    dados *d = (dados*)arg;	
    
    printf("Thread %d escreveu o nome: %s, morada: %s e o número: %d.\n",d->pos+1, d->nome, d->morada, d->numero);

    pthread_exit((void*)NULL);
}


int main(){
    pthread_t threads[5];
    int i;
    dados args[5];
    

    //preenche os dados da estrutura que vao ser passados por parametro cria a thread
    for(i=0;i<5;i++){
      args[i].numero = i + 1;
      sprintf(args[i].nome, "Nome %d", i + 1);
      sprintf(args[i].morada, "Morada %d", i + 1);
      args[i].pos = i;

  }

  for(i=0;i<5;i++){
      pthread_create(&threads[i],NULL,thread_func,(void*)&args[i]);
  }

  for(i=0;i<5;i++){
    pthread_join(threads[i],NULL);
}

return 0;
}
