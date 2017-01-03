#include <stdio.h>
#include "insere_notas.h" 

void imprimirAluno(Aluno *p){
	printf("Aluno:\nIdade %d\nNumero %d \n",p -> idade,p -> numero);
	int i = 0;	
	for (i = 0;i<10;i++){
		printf("Nota %d = %d\n",i,p -> notas[i]);
	}
	printf("Nome: %s\nMorada:  %s\n",p -> nome,p -> morada);
}

int main(void) {
	Aluno a;
	Aluno * p;
	p = &a;
	int numero = 1224422;
	char nome[] = "Joao Francisco";
	char rua[] = "Rua do Joao Francisco";
	int novasNotas[10] = {10,9,8,7,6,5,4,3,2,1};
	int idade = 19;
	insere_dados(p,numero,nome,rua,idade);
	int resultado; 
	insere_notas(p,novasNotas);
	imprimirAluno(p);


	return 0;
}

