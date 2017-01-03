#include <stdio.h>

void intBin(int x)
{
	int larg = sizeof(int) * 8;
	char bits[larg];
        int n = 1 << (larg - 1), i = 0;

        for ( ; n!=0 ; n=(unsigned int)n>>1 ) {
                bits[i] = x & n ? '1' : '0';
                i++;
        }
        bits[i] = '\0';
	printf("%s\n",bits);
}
