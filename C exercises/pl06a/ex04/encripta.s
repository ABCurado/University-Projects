.section .data
.global frase
.section .text
.global encripta 
encripta:
	movl $frase, %edx #coloca o apotador da primeira frase em %edx
	movl $0, %eax
 
encripta_frase:
	cmpb $0, (%edx) #caso seja o fim da string acaba 
	je fim 
	cmpb $' ', (%edx) #caso seja um espaço nada altera
	je salta
	incb (%edx) #encripta adicionando 1 ao valor apontado pela string
	incl %eax
	incl %edx
	jmp encripta_frase

salta:	
	incl %edx
	jmp encripta_frase

fim:	
	ret
