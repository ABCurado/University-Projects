.section .text
	.global imprime_resultado
	.global calcula

calcula:
	# prologo
	pushl	%ebp
	movl	%esp,%ebp
	subl	$8, %esp		# int soma, multiplica; (variaveis locais)

	# soma = (a+b)
	movl	12(%ebp), %eax
	addl	8(%ebp), %eax		# a+b
	movl	%eax, -4(%ebp)		# soma <- (a+b)

	# multiplica = (a*b)
	movl    12(%ebp), %eax
	cdq
	movl	8(%ebp), %ecx
	imull	%ecx			# a*b em %eax:%edx
	movl	%eax, -8(%ebp)		# multiplica <- (a*b)

	# imprime_resultado('+', a, b, soma)
	pushl	-4(%ebp)
	pushl	12(%ebp)
	pushl	8(%ebp)
	pushl	$'+'
	call imprime_resultado

	# imprime_resultado('*', a, b, multiplica);
	pushl	-8(%ebp)
	pushl   12(%ebp)
	pushl   8(%ebp)
	pushl	$'*'
	call imprime_resultado

	# return (a*b) - (a+b)
	movl	-4(%ebp), %eax
	subl	-8(%ebp), %eax

	addl	$8, %esp	# descartar vars locais

	#epilogo
	movl	%ebp,%esp
	popl	%ebp
	ret
