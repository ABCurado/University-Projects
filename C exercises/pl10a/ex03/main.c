#include "aluno.h"
#include "altera_morada.h"

int main(void)
{

	Aluno alunos[5];
	Aluno * p;
	p=alunos;

	insere_dados(p,1140780,"Ruben","Rua das Pedras",29);
	insere_dados(++p,1140781,"João","Rua do Alcatrão",30);
	insere_dados(++p,1140782,"Joaquim","Rua da Areia",31);
	insere_dados(++p,1140783,"Maria","Rua do Paralelo",32);
	insere_dados(++p,1140784,"Joana","Rua dos Buracos ",33);

	p=alunos;
	Aluno * i;
	for (i=p;i<(p+5);i++)
		imprime_dados(i);


	char novaMorada[] = "Nova Rua";
	altera_morada(p,novaMorada);
	imprime_dados(p);

	char novaMorada2[] = "Morada grande de mais para caber no espaço reservado na estrutura........................................................................................................................................................................................................................................................................................";
	altera_morada(p,novaMorada2);
	imprime_dados(p);

	return 0;
}
