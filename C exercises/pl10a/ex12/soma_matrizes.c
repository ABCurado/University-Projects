#include <stdlib.h>
#include "soma_matrizes.h"

void soma_matrizes(int **a, int **b, int **resultado,int numberOfLines, int numberColumns){
	for (int rows=0; rows < numberOfLines; rows++){
    	for(int columns=0; columns < numberColumns; columns++){	
			*(*(resultado+rows)+columns) = *(*(a+rows)+columns)+*(*(b+rows)+columns);
		}
 	}
}

