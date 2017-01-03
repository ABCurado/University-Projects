.section .data
	.global op1
	.global op2
	.global res

.section .text
	.global multiplicacao

multiplicacao:
	movl	op1, %eax		# 1º operando em eax
	movl	op2, %ebx		# 2º em ebx

	imull	%ebx, %eax		# 1º * 2º - resultado em eax

	movl	%eax, res		#copia para a variável res
	ret
