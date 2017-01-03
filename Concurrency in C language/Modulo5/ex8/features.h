
#include <time.h>

int generateRandom(int min, int max, int seed);
int * generateArray(int pos, int minValue, int maxValue);
int * generateArrayDifferentValues(int pos);
int ** generateMultiDimentionalArray(int a, int b, int minValue, int maxValue);
char * getCurrentTime();
void * createDynamicArray(int pos, size_t typeSize);
void ** createDynamicMatrix(int rows, int columns, size_t typeSize);
void arraySort(int ** array, int rows, int columns);

int generateRandom(int min, int max, int seed) {
	srand(time(NULL)+seed);
	return ((rand()%(max-min))+min);
}

int * generateArray(int pos, int minValue, int maxValue) {
	int *array = (int*)malloc (sizeof (int) * pos);
	int i;
	for (i = 0; i < pos; ++i) {
		array[i] = generateRandom(minValue, maxValue, i);
	}
	return array;
}

int * generateArrayDifferentValues(int pos) {
	int *array = (int*)malloc (sizeof (int) * pos);
	int i;
	for (i = 0; i < pos; ++i) {
		array[i] = i+1;
	}
	return array;
}

int ** generateMultiDimentionalArray(int a, int b, int minValue, int maxValue) {
    int **array = (int**)malloc(sizeof(int*) * a);
    int i;
    for (i = 0; i < a; ++i) {
        array[i] = generateArray(b, minValue, maxValue);
    }
    return array;
}

char * getCurrentTime() {
	char * timeString = (char *) malloc(sizeof(char) * 20);
	time_t now = time(NULL);
	strftime(timeString, 20, "%H:%M:%S", localtime(&now));
	return timeString;
}

void * createDynamicArray(int pos, size_t typeSize) {
	void * array = (void*)malloc(typeSize * pos);
	return array;
}

void ** createDynamicMatrix(int rows, int columns, size_t typeSize) {
	void ** matrix = (void**)malloc(typeSize * rows);
    int i;
    for (i = 0; i < rows; ++i) {
        matrix[i] = createDynamicArray(columns, typeSize);
    }
    return matrix;
}

void arraySort(int ** matrix, int rows, int columns) {
	int i;
	for (i = 0; i < rows - 1; ++i) {
		int j;
		for (j = i + 1; j < rows; ++j) {
			if(matrix[i][columns-1] > matrix[j][columns-1]) {
				int * tmp = matrix[i];
				matrix[i] = matrix[j];
				matrix[j] = tmp;
			}
		}
	}
}
