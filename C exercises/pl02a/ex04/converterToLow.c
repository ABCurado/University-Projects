#include <stdio.h>

void converteToLow(char * ap) {
	while (*ap != 0) {
		if (*ap > 'A' && *ap < 'Z') {
			*ap += ('a' -'A');
		}
		ap++;
	}
}
