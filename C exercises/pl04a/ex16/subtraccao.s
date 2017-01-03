.section .data
	.global op1
	.global op2
	.global res

.section .text
	.global subtraccao

subtraccao:
	movl	op1, %eax
	movl	op2, %ebx
	subl	%ebx, %eax	#subtrai o valor armazenado em ebx ao
				#valor em eax e guarda o valor em eax

	movl %eax, res		#copia para a variável res
	ret
