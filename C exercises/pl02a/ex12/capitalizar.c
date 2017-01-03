#include <stdio.h>


/* Capitaliza uma frase ou seja, converte para upper case a primeira e todas
 * as primeiras letras das palavras da frase a menos que ja sejam maiusculas
 */

void capitalizar(char * ap) {
	char anterior; /* variavel auxiliar que ira conter o carater avaliado anteriormente no ciclo while */

	/* Capitalizar a primeira letra da frase */
	if (*ap > 'a' && *ap < 'z')
		*ap -= ('a' -'A');

	anterior = *ap;
	ap++;

	/* capitalizar o resto da frase*/
	while (*ap != 0) {

		/*Se o char anterior for um espaço, estamos no inicio de uma nova palavra*/
		if ((anterior == ' ') && (*ap > 'a') && (*ap < 'z'))
			*ap -= ('a' -'A');

		anterior = *ap;
		ap++;
	} 
}
