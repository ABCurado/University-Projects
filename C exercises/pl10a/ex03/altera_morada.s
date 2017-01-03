.section .data
	.equ	MORADA_DESCOLAMENTO, 128	# a morada 48 bytes depois
	.equ	TAM_MORADA, 120			# fim da morada 120 bytes depois

.section .text
	.global altera_morada

altera_morada:
	# prologo
	pushl	%ebp
	movl	%esp,%ebp
	pushl	%ebx

	movl	12(%ebp), %ecx				# apontador (char *) para nova morada
	movl	8(%ebp), %edx				# apontador para Aluno *
	leal	MORADA_DESCOLAMENTO(%edx), %edx		# apontar para a morada de Aluno *

ciclo:
	cmpl	$0, (%ecx)		#teste a cabeca do while (char = 0 ?)
	je	fim
	leal	MORADA_DESCOLAMENTO+TAM_MORADA+8(%ebp), %ebx
	cmpl	%ebx, %edx				# verificar se chegamos ao final
	je	truncar					# do vetor de char da estrutura (truncando se for o caso)

	movl	(%ecx), %eax		# copiar o char para eax
	movl 	%eax, (%edx)		# copiar char de edx para morada de Aluno *

	incl	%ecx
	incl	%edx
	jmp	ciclo

truncar:
	movl	$0, (%edx)

fim:
	#epilogo
	popl	%ebx
	movl	%ebp,%esp
	popl	%ebp
	ret
