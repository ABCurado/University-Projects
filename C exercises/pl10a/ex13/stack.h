typedef struct stack_node stack_node;

struct stack_node{
        int data;
        struct stack_node * next;
};

int pop(stack_node **);
void push(stack_node **,int);
