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
        printf("\nNumero: %d\n", p->numero);
        printf("Nome: %s\n", p->nome);
        printf("Idade: %d\n", p->idade);
        printf("Morada: %s\n", p->morada);
        printf("Notas: \n");
}
