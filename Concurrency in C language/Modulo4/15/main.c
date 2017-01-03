#include <stdio.h> 
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include <semaphore.h>
#include <time.h>

#define NR_CARS 15
#define SEM_EAST "/semaphore_ex15_east"
#define SEM_EAST_LOCKER "/semaphore_ex15_EL"
#define SEM_WEST_LOCKER "/semaphore_ex15_WL"
#define SEM_WEST "/semaphore_ex15_west"
#define SEM_QTD "/semaphore_ex15_qtd"


sem_t* create_sem(char* name, int value){
	sem_t* semaforo;
	if((semaforo = sem_open(name, O_CREAT, 0644, value)) == SEM_FAILED) {
        perror("sem_open()");
		exit(1);
	}
	return semaforo;
}

void close_sems(sem_t* east_sem, sem_t* west_sem, sem_t* east_blocker, sem_t* west_blocker){
	sem_close(east_sem);
	if(sem_unlink(SEM_EAST) == -1){
		perror("sem_unlink()");
		exit(1);
	}

	sem_close(west_sem);
	if(sem_unlink(SEM_WEST) == -1){
		perror("sem_unlink()");
		exit(1);
	}

	sem_close(east_blocker);
	if(sem_unlink(SEM_EAST_LOCKER) == -1){
		perror("sem_unlink()");
		exit(1);
	}

	sem_close(west_blocker);
	if(sem_unlink(SEM_WEST_LOCKER) == -1){
		perror("sem_unlink()");
		exit(1);
	}
}

int generate_random(int var, int min, int max){
	srand(time(NULL)+var);
	return ((rand()%max)+min);
}

pid_t create_child(){
	pid_t p = fork();
	if(p == -1){
		perror("fork()");
		exit(1);
	}
	return p;
}

int create_childs(){
	int east = 0, west = 0, i, way;
	for(i = 0; i < NR_CARS; i++){
		way = generate_random(i, 1, 2);
		if(way == 1){
			east++;
		} else {
			west++;
		}
		pid_t p = create_child();
		if(p == 0)
			return way;
	}
	printf("Created %d cars on Eastside.\n", east);
	printf("Created %d cars on Westside.\n", west);
	return 0;
}


int get_val(sem_t* sem){
	int val;
	if(sem_getvalue(sem, &val) == -1){
		perror("sem_getvalue()");
		exit(1);
	}
	return val;
}

void cross_bridge(int way, sem_t* waysem){
	sem_post(waysem);
	int sleeptime = generate_random(getpid(), 1, 5);
	printf("Car crossing bridge from %s to %s (Taking %d seconds)\n", (way==1)?"East":"West", (way==2)?"East":"West", sleeptime);
	sleep(sleeptime);
	sem_wait(waysem);
}

void try_cross(int way, sem_t* waysem, sem_t* other_blocker, sem_t* this_blocker){
	sem_wait(this_blocker);
	sem_post(this_blocker);
	sem_trywait(other_blocker);
	cross_bridge(way, waysem);
	if(get_val(waysem) == 0){
		printf("Unblocked cars on %s side.\n", (way==1)?"West":"East");
		sem_post(other_blocker);
	}
}

int main(int argc, char *argv[]){
	int i;

	sem_t* east_sem = create_sem(SEM_EAST, 0);
	sem_t* west_sem = create_sem(SEM_WEST, 0);
	sem_t* west_blocker = create_sem(SEM_WEST_LOCKER, 0);
	sem_t* east_blocker = create_sem(SEM_EAST_LOCKER, 0);

	int id = create_childs();
	
	if(id == 1){ //EAST TO WEST
		try_cross(id, east_sem, west_blocker, east_blocker);
		exit(0);
	} else if(id == 2){ //WEST TO EAST
		try_cross(id, west_sem, east_blocker, west_blocker);
		exit(0);
	}
	sem_post(east_blocker);
	sem_post(west_blocker);

	for(i = 0; i<NR_CARS; i++){
		wait(NULL);
	}

	close_sems(east_sem, west_sem, west_blocker, east_blocker);

	return 0;
}