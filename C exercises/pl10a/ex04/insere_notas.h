typedef struct{
	int idade;
	int numero;
	int notas[10];
	char nome[80];
	char morada[120];
} Aluno;

void insere_notas(Aluno *p,int *novas_notas);
void insere_dados(Aluno *p, int numero, char *nome,char *morada, int idade);

