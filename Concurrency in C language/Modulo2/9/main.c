#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <time.h>
#include <unistd.h>

#define READ 0
#define WRITE 1

void createChild(int * infoPipe, int * gamePipe) {
    pid_t childpid;

    if((childpid = fork()) == -1)
    {
        perror("fork");
        exit(1);
    }
    if(childpid == 0)
    {
        close(infoPipe[WRITE]);
        close(gamePipe[READ]);

        runChildGame(infoPipe, gamePipe);

        // Finish game 
        close(infoPipe[READ]);
        close(gamePipe[WRITE]);
        exit(0);
    }
}

void runChildGame(int * infoPipe, int * gamePipe) {
    int runGame;
    int money;
    do {
        if( read(infoPipe[READ], &runGame, sizeof(int)) <= 0) {
            perror("Pipe read error.");
            exit(1);
        }

        // Start the gamble
        if(runGame) {
            int min = 1, max = 5;
            srand(time(NULL));
            int random = rand() % max + min; // generate number between 1 and 5
            write(gamePipe[WRITE], &random, sizeof(random));

            if (read(infoPipe[READ], &money, sizeof(int)) <= 0) {
                perror("Pipe read error.");
                exit(1);
            }
        }

    }while(runGame && money>0);
}

void parentRunGame(int * infoPipe, int * gamePipe) {

    close(infoPipe[READ]);
    close(gamePipe[WRITE]);

    runParentGame(infoPipe, gamePipe);

    printf("\n\n...ENDING PROGRAM...\n");
    // Finish game 
    close(infoPipe[WRITE]);
    close(gamePipe[READ]);
    int status;
    wait(&status);
}

void runParentGame(int * infoPipe, int * gamePipe) {
    int money = 20;
    int num;

    do {
        showMoney(money);
        int value;
        printf("Insira valor (1-5): ");
        scanf("%d",&value);

        // 
        if(value == -1) {
            num = 0;
        } 
        // 
        else {
            num = 1;
        }

        write(infoPipe[WRITE], &num, sizeof(num));
        if(num) {
            int random;
            if( read(gamePipe[READ], &random, sizeof(int)) <= 0) {
                perror("Pipe read error.");
                exit(1);
            }

            if(random==value) {
                printf("\n! CONGRATZ, YOU WON !\n\n");
                money += 10;
            } else {
                printf("\nSorry, you lost ...\nThe number was: %d\n\n",random);
                money -= 5;
            }

            write(infoPipe[WRITE], &money, sizeof(money));
        }

    }while(money>0 && num);
}

void showMoney(int money) {
    printf("Your Money: %d\n", money);
}

int main (void){
    int     fd1[2];
    int     fd2[2];

    printf("\nGAMBLE GAME\n\n");

    if(pipe(fd1) == -1){
        perror("Pipe1 failed");
        return 1;
    }

    if(pipe(fd2) == -1){
        perror("Pipe2 failed");
        return 1;
    }

    createChild(fd1, fd2);
    parentRunGame(fd1, fd2);
    
    return(0);
}

