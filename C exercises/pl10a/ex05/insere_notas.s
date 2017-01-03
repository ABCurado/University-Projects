.section .data
	.equ	NOTAS_DESCOLAMENTO, 8		# as notas 8 bytes depois

.section .text
	.global insere_notas

insere_notas:
	# prologo
	pushl	%ebp
	movl	%esp,%ebp
	pushl	%ebx

	movl	12(%ebp), %ecx				# apontador (int *) para o vector das notas a inserir
	movl	8(%ebp), %edx				# apontador para Aluno *
	leal	NOTAS_DESCOLAMENTO(%edx), %edx		# apontar para o vector notas de Aluno *
	movl	$0, %ebx				# contador para o ciclo

ciclo:
	cmpl	$10, %ebx		# teste a cabeca do while (cont < 10) assumindo que o vector recebido
	je	fim			# tem sempre 10 valores

	movl	(%ecx), %eax		# copiar o valor para eax
	movl 	%eax, (%edx)		# copiar valor de edx para vetor notas de Aluno *

	leal	4(%ecx), %ecx
	leal	4(%edx), %edx
	incl	%ebx
	jmp	ciclo

fim:
	#epilogo
	popl	%ebx
	movl	%ebp,%esp
	popl	%ebp
	ret
