#include <stdio.h>
#include <stdlib.h>
#include "stack.h" 

int main(){
	stack_node *TOP;
	int x;
	TOP=NULL; 
	printf("Push :1\n");
	push(&TOP,1);
	printf("Push :2\n");
	push(&TOP,2);
	printf("Push :3\n");
	push(&TOP,3); 
	x=pop(&TOP);
	printf("Pop :%d\n",x);
	x=pop(&TOP);
	printf("Pop :%d\n",x);
	x=pop(&TOP);
	printf("Pop :%d\n",x);
	x=pop(&TOP);
	printf("Pop :%d\n",x);

	return 0;
} 

