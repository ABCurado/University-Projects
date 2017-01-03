.section .data
.global ptrvec
.global num
.global num_pretendido
.section .text
.global vec_search 
vec_search:
	movl $ptrvec, %eax #coloca o apontador em eax
	movw num, %bx
	movl $0, %edx
	movl $0, %ecx
	movw num_pretendido, %cx
	jmp procurar
 
procurar:
	cmpw %bx, %dx #caso tenha chegado ao fim do vetor
	je nao_encontrou
	cmpw (%eax),%cx #caso encontre retorna eax que contem a posição de memoria
	je fim
	addl $2, %eax
	incw %dx
	jmp procurar

nao_encontrou:
	movl $0, %eax

fim:	
	ret
