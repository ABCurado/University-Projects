#include <time.h>

int generateRandom(int min, int max, int seed);
int * generateArray(int pos, int minValue, int maxValue);
int * generateArrayDifferentValues(int pos);
int ** generateMultiDimentionalArray(int a, int b, int minValue, int maxValue);

int generateRandom(int min, int max, int seed) {
	//srand(time(NULL)+seed);
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
