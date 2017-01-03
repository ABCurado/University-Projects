#include <stdlib.h>
#include "procura_matriz.h"

int procura_matriz(int **m, int numero, int numberOfLines, int numberColumns){
	for (int rows=0; rows < numberOfLines; rows++){
    	for(int columns=0; columns < numberColumns; columns++){	
			if(numero == m[rows][columns])
				return 1;
		}
 	}
	return 0;
}

