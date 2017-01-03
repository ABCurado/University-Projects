
int activa_esquerda_direita_em_C(int a, int dir, int esq ){
	int pos = 0;
	if(dir > esq){
		return 0 ;
 	}
	while(pos < 32){
		/*Caso a posicao seja superior a esquerda ou inferior á direita poe o bit a zero*/
		if(pos < dir || pos > esq){a |= 1 << pos;}
			pos++;
	}
	return  a ;
}
