#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>


float playerMoney;

int executeBatota() {
	pid_t batota;
	if ((batota=fork()) == 0) { // IF CHILD
		int value = -1;
		char *const params[] = {"batota", NULL};
		value = execv("./batota", params);
		exit(value);
	}
	int status;
	wait(&status);
	int tmp = WEXITSTATUS(status);
	printf("%d\n", tmp);
	return tmp;
}

void showMoney() {
	printf("\n*** PLAYER MONEY: %.2f ***\n", playerMoney);
}

void play(float playerBet, int playerNumber) {
	int programNumber;
	do {
		printf("CHECK\n");
		programNumber=executeBatota();
	}while(programNumber<=0);
	if(playerNumber==programNumber) {
		playerMoney += playerBet*2;
		printf("!! CONGRATZ !! YOU WON !!\n");
	} else {
		playerMoney -= playerBet;
		printf("Your number was: %d\n", playerNumber);
		printf("The generated value: %d\n", programNumber);
		printf("\nIf you keep playing, at some point you should win\n");
	}
}

void runGame() {
	printf("\n\n\n\tWELCOME TO THIS AWESOME GAME\n\n");
	while(playerMoney!=0) {
		showMoney();

		float bet;
		do {
			printf("Enter Bet Value (Not max than your money): ");
	   		scanf("%f", &bet);
	   	}while(bet<=0 || bet>playerMoney);

   		int guessNumber;
   		do {
			printf("Enter Number to Guess (1-5): ");
	   		scanf("%d", &guessNumber);
   		}while(guessNumber<1 || guessNumber>5);

   		play(bet, guessNumber);
	}
	printf("\nYOU LOST ALL YOUR MONEY\nTHE GAME IS OVER ... \n\n");
}

int main (void){
	playerMoney=25.0;
	runGame();
	return 0;
}