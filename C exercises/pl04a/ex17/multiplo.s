.section .data
	.global op1
	.global op2
	.global res
	.section .text
	.global multiplo

multiplo:
	movl op1, %eax
	movl op2, %ebx
	movl $0, %edx
	divl %ebx #Caso a divisao de um pelo outro seja 0 podemos afirmar que op2 é multiplo de op1
	cmpl $0, %edx
	jne nao_e_multiplo
	je fim

nao_e_multiplo:
	movl $1, %edx

fim:
	movl %edx, res
	ret
