int soma_multiplos_x_em_C(char *vec, int x){
	int num =0;
	num |= x >> 15; /*poe em num apenas o 3º Byte de x */
	num &= 0xFF;
	int soma = 0 ,contador = 0;
	while(*(vec+contador) != 0){ /*Corre o vetor e caso o resto da divisao seja 0 adiona a soma*/
		if(*(vec+contador) % num == 0){ soma += *(vec+contador);}
		contador++;
	}
	return soma;
}
