.section .text
	.global conta_bits_um

conta_bits_um:
	# prologo
	pushl	%ebp
	movl	%esp,%ebp

	movl	8(%ebp), %edx		# parametro em edx
	movl	$0, %eax		# contador = 0

while:
	cmpl	$0, %edx		# x=0 : return
	je	fim

	testl	$1, %edx		# x & 1 = 1 ?
	jne	conta_bit		# se sim conta_bit
	shr	%edx			# >> 1 (proximo bit)
	jmp	while

conta_bit:
	incl	%eax
	shr	%edx
	jmp	while

fim:
	#epilogo
	movl	%ebp,%esp
	popl	%ebp
	ret
