.section .text
	.global testa_iguais

testa_iguais:
	# prologo
	pushl	%ebp
	movl	%esp,%ebp
	pushl	%ebx

	movl	12(%ebp), %ecx		# addr 2nd param
	movl    8(%ebp), %edx           # addr 1ro param
	movl	$1, %eax		# assume-se string iguais (testa-se em ciclo)

teste:
	cmpb	$0, (%ecx)
	je	fim
	cmpb	$0, (%edx)
	je	fim

ciclo:
	movb	(%ecx), %bl		# 2nd param em bl
	cmpb	(%edx), %bl		# char1 = char2 ?
	jne	diferentes
	incl	%ecx
	incl	%edx
	jmp	teste

diferentes:
	movl	$0, %eax

fim:
	#epilogo
	popl	%ebx
	movl	%ebp,%esp
	popl	%ebp
	ret
