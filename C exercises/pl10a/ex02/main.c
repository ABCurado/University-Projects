#include <stdio.h>
#include "insere_dados.h" 

void imprimirAluno(Aluno *p){
	printf("Aluno:\nIdade %d\nNumero %d \n",p -> idade,p -> numero);
	printf("Nome: %s\nMorada:  %s\n",p -> nome,p -> morada);
}

int main(void) {
	Aluno a[5];
	Aluno * p;
	p = a;
	int numero = 1224422;
	char nome[] = "Joao Francisco";
	char rua[] = "Rua do Joao Francisco";
	int idade = 19;
	int i;
	for(i=0;i<5;i++){
		insere_dados((p+i),numero,nome,rua,idade);
	}
	for(i=0;i<5;i++){
		imprimirAluno(p+i);
	}


	return 0;
}

