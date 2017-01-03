.section .data
	.global op1
	.global op2
	.global res

.section .text
	.global soma

soma:
	movl	op1, %eax
	movl	op2, %ebx
	addl	%ebx, %eax		#soma o valor armazenado em ebx com
					#eax e guarda o valor em eax

	movl	%eax, res		#copia para a variável res
	ret
