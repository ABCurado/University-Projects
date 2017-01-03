# Makefile desenvolvida por mim com base na fornecida nos slides de arqcp e com recurso á internet

# variaveis
 INCLUDES =  $(wildcard *.h) 
 SOURCES =  $(wildcard *.c)
 OBJFILES =  $(SOURCES:.c=.o)
 EXEC = ex17

# regraes de sufixo

%.o: %.c $(INCLUDES)
	gcc -c -o $@ $< -Wall -I .
#Compila todos os programas .c para binarios .o 
# $@    Nome da regra acima mencionada.
#$<    Nome da primeira dependência 

$(EXEC): $(OBJFILES)
	gcc -o $@ $^ -Wall -I . 
#compila o programa com o nome atribuido a EXEC recorrendo aos OBJFILES da pasta atual
# $@    Nome da regra acima mencionada.
# $^     Lista de dependências

run: ${EXEC}
	./${EXEC}

clean:
	rm -f ${OBJFILES}
