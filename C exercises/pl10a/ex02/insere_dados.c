#include <string.h>
#include "Aluno.h"

void insere_dados(Aluno *p, int numero, char *nome,char *morada, int idade){
	p -> numero = numero;
	strcpy(p->nome, nome);
	strcpy(p->morada, morada);
	p -> idade = idade;
}
