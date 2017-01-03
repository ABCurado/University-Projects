.section .text
	.global menor

menor:

	# prologo
	pushl	%ebp
	movl	%esp,%ebp

	leal    8(%ebp), %edx	# addr do 1º param em edx
	movl    (%edx), %eax	# valor do 1º param em eax

	addl	$4, %edx	# addr 2º param em edx
	call	teste		# testando 2º com 1º param

	addl	$4, %edx	# addr do 3º param em edx
	call	teste		# testando 3º com o menor dos primeiros dois params

	jmp	fim

teste:
	cmpl	(%edx), %eax	# eax > edx ?
	jg	menor_encontrado
	ret

menor_encontrado:
	movl	(%edx), %eax	# eax = novo menor apontado por edx
	ret

fim:
	#epilogo
	movl	%ebp,%esp
	popl	%ebp
	ret
