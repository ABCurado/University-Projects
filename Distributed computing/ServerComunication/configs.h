#define BCAST_PORT 15978
#define BCAST_REQUEST "SendYourFiles"
#define BCAST_REQUEST_TIMER 30
#define BCAST_ANSWERS_WAIT_TIME 5
#define MESSAGE_MAX_LENGTH 512
#define EVENT_TO_HTTP_PORT 15977
#define EVENT_TO_HTTP_MESSAGE "RefreshThePage"
#define FILES_ROOT_FOLDER "../www/files"

// ler stdin a frase com menos de S carateres para o buffer B
#define GETS(B,S) {fgets(B,S-2,stdin);B[strlen(B)-1]=0;}

typedef struct {
	char name[150];
	char path[250];
}File;