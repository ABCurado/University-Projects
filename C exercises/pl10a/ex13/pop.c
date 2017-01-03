#include <stdlib.h>
#include "stack.h"

int pop(stack_node **p_stack){
	if (*p_stack == NULL)
		return -1;
	int num;
	stack_node * node;
	node=*p_stack;
	*p_stack=node->next;
	num=node->data;
	free(node);
	return(num);
} 


