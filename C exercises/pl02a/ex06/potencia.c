/* Conversao de vetor de char para inteiro */

int toInt(char input[]) {
        int c = 0, output = 0;
        while (input[c] != 0) {
                output = output * 10 + input[c] - '0';
                c++;
        }
        return output;
}

/* Funcao de potencia */
void potencia_por_referencia(int * x, int y) {
	int base = *x;

	if (y == 0) {
		*x = 1;
	} else {
		while (y > 1) {
			*x *= base;
			y--;
		}
	}
}
