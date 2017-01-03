#include <stdlib.h>
#include "stack.h"

void push(stack_node **p_stack,int num){
	stack_node *new_node;
	new_node=(stack_node *)malloc(sizeof(stack_node));
	new_node->data = num;
	new_node->next = *p_stack;
	*p_stack=new_node;
} 

