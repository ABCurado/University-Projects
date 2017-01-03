.section .data
	.global larg
	.global res
	.section .text
	.global soma #int soma(void)

soma:
	movl res, %eax
	movl larg, %ebx
	addl %ebx, %eax #soma o valor armazenado em larg com eax e
	#guarda o valor em eax

	movl %eax, res # copia para a variável res
	ret
