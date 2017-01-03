/* Dado um vetor em que o seu primeiro elemento é o numero de algarismos contidos,
 * a função devolve o somatorio dos elementos pares
*/

int somarPares(int * ptr) {
	int soma = 0, * i, tam = *ptr;

	for (i = ptr+1; i <= (ptr+tam); i++)
		if (*i % 2 == 0) {soma += *i;}
	return soma;
}
