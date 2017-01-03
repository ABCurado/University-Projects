#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <time.h>
#include <unistd.h>

#define CHILD_CREATE 2
#define PRODUCTS_NUM 5
#define MESSAGE_SIZE 20
#define READ 0
#define WRITE 1

typedef struct {
    int     pipeValue;
    char    barcode[MESSAGE_SIZE];
}Data;

typedef struct {
    int     hasItem;
    char    barcode[MESSAGE_SIZE];
    char    name[MESSAGE_SIZE];
    float   price;
}Product;

void createChild(int * pipe, int childNum) {
    pid_t childpid;

    if((childpid = fork()) == -1)
    {
        perror("fork");
        exit(1);
    }
    if(childpid == 0)
    {
        int childReadData = READ;
        int childWriteData = WRITE;
        int childReadProduct = READ + (childNum * 2) + 2;
        int childWriteProduct = WRITE + (childNum * 2) + 2;

        close(pipe[childReadData]);
        close(pipe[childWriteProduct]);

        char code[MESSAGE_SIZE];
        printf("\nCodigo: ");
        fgets(code, MESSAGE_SIZE, stdin);
        code[strlen(code)-1] = 0;

        Data dat;
        dat.pipeValue = childWriteProduct;
        strcpy(dat.barcode, code);

        write(pipe[childWriteData], &dat, sizeof(Data));

        Product prod;
        if( read(pipe[childReadProduct], &prod, sizeof(Product)) <= 0) {
            perror("Pipe read error.");
            exit(1);
        }

        if( prod.hasItem == 1 ) {
            printf("Código de Barras: %s\nNome: %s\nPreco: %.2f €\n",
                prod.barcode, prod.name, prod.price);
        } else {
            printf("Nenhum produto encontrado.\n");
        }

        close(pipe[childWriteData]);
        close(pipe[childReadProduct]);
        exit(0);
    }
}

Product* findProduct(Product * products, char * barcode) {
    int i;
    for (i = 0; i < PRODUCTS_NUM; ++i) {
        if(strcmp(barcode, products[i].barcode)==0) {
            return &products[i];
        }
    }
    Product prod;
    prod.hasItem = 0;
    return &prod;
}

void createProducts(Product * products) {
    Product tmp1,tmp2,tmp3,tmp4,tmp5;

    // Product 1
    tmp1.hasItem = 1;
    strcpy(tmp1.barcode,"12345");
    strcpy(tmp1.name,"Arroz");
    tmp1.price=0.89;
    products[0] = tmp1;

    // Product 2
    tmp2.hasItem = 1;
    strcpy(tmp2.barcode,"12223");
    strcpy(tmp2.name,"Feijão");
    tmp2.price=0.65;
    products[1] = tmp2;

    // Product 3
    tmp3.hasItem = 1;
    strcpy(tmp3.barcode,"11113");
    strcpy(tmp3.name,"1Kg Batatas");
    tmp3.price=10.0;
    products[2] = tmp3;

    // Product 4
    tmp4.hasItem = 1;
    strcpy(tmp4.barcode,"18915");
    strcpy(tmp4.name,"Água 5L");
    tmp4.price=1.00;
    products[3] = tmp4;

    // Product 5
    tmp5.hasItem = 1;
    strcpy(tmp5.barcode,"21923");
    strcpy(tmp5.name,"Sumo 1.5L");
    tmp5.price=1.20;
    products[4] = tmp5;
}

int main (void){
    int     fd[(CHILD_CREATE*2) + 2];
    int     i;

    printf("\nLeitor de Código de Barras.\n\n");

    for (i = 0; i < CHILD_CREATE + 1; ++i) {
        if(pipe(fd + (i*2)) == -1){
            perror("Pipe failed");
            return 1;
        }
    }

    Product products[PRODUCTS_NUM];
    createProducts(products);

    for (i = 0; i < CHILD_CREATE; ++i) {
        createChild(fd, i);

        close(fd[(READ + (i * 2) + 2)]);

        Data dat;

        if (read(fd[READ], &dat, sizeof(Data)) <= 0) {
            perror("Pipe read error.");
            exit(1);
        }

        Product * tmpProd;
        tmpProd = findProduct(products, dat.barcode);

        write(fd[dat.pipeValue], tmpProd, sizeof(Product));

        close(fd[dat.pipeValue]);

        int status;
        wait(&status);
    }

    printf("\n");
    close(fd[WRITE]);
    close(fd[READ]);
    
    return(0);
}

