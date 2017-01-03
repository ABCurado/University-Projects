#include <stdio.h>
#include <stdlib.h>

long calculateFactorial(int n) {
    if (n == 0)
        return 1;
    else
        return (n * calculateFactorial(n-1));
}

int main(int argc, char **argv){
    // Convert string to int
    int number = atoi(argv[1]);
    long fact;

    if (number < 0) {
        printf("Negative numbers allowed.\n");
        return 1;
    } else {
        fact = calculateFactorial(number);
        printf("%ld\n", fact);
    }
    
    return 0;
}

