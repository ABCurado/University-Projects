#include <stdio.h>
#include <string.h>
#include "aluno.h"

void insere_dados(Aluno *p, int numero, char *nome, char *morada, int idade);
void imprime_dados(Aluno *p);


int main(void)
{
	Aluno a;
	Aluno * p;
	p=&a;

	int numero = 1140780;
	char nom[] = "Ruben Teixeira";
	char * nome = nom;
	char mor[] = "S. Gonçalo, Amarante";
	char * morada = mor;
	int idade = 29;

	insere_dados(p,numero,nome,morada,idade);
	imprime_dados(p);

	return 0;
}

void insere_dados(Aluno *p, int numero, char *nome, char *morada, int idade)
{
	p->numero = numero;
	strcpy(p->nome, nome);
	strcpy(p->morada, morada);
	p->idade = idade;

}

void imprime_dados(Aluno *p)
{
	printf("Numero: %d\n", p->numero);
	printf("Nome: %s\n", p->nome);
	printf("Idade: %d\n", p->idade);
	printf("Morada: %s\n", p->morada);
	printf("Notas: ");
}
