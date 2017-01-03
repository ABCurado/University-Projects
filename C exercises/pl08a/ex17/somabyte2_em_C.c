void somabyte2_em_C(char x, int *vec1,int *vec2){
	int contador;
	*vec2 = *vec1;
	/*Itera o vetor á exceção da primeira que contem o numero de posiçoes*/
	for(contador = 1; contador < (*vec1)+1; contador++){
		int temporario =0,mascara;
		mascara = 0xFF00;
		temporario = *(vec1+contador); /*guarda o vetor em temporario*/
		*(vec2+contador) = temporario & ~mascara;/*Guarda em vec2 a posiçao de vec sem O segundo byte*/
		temporario &= mascara; /*Guarda em temporario o segundo byte adiciona x */
		temporario = temporario >> 8;
		temporario += x;
		temporario = temporario << 8;
		*(vec2+contador) |= temporario ; /*Coloca o segundo byte com x adicionado á posição*/
	}		
}


