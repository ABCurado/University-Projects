#include <stdio.h>
#include <string.h>
#include "aluno.h"

void insere_dados(Aluno *p, int numero, char *nome, char *morada, int idade)
{
        p->numero = numero;
        strcpy(p->nome, nome);
        strcpy(p->morada, morada);
        p->idade = idade;

}

void imprime_dados(Aluno *p)
{
	printf("\nNumero:\t%d\n", p->numero);
	printf("Nome:\t%s\n", p->nome);
	printf("Idade:\t%d\n", p->idade);
	printf("Morada:\t%s\n", p->morada);
	printf("Notas:\t[");

	int i;
	for (i=0;i<10;i++)
		printf(" %d",p->notas[i]);

	printf(" ]\n");
}
