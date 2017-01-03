.section .data
	.global op1
	.global res
	.section .text
	.global subtraccao

subtraccao:
	movl res, %eax
	movl op1, %ebx
	subl %ebx, %eax		#subtrai o valor armazenado em ebx ao valor
				#em eax e guarda o valor em eax

	movl %eax, res		#copia para a variável res
	ret
