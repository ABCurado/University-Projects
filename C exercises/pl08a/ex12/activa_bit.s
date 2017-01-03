.section .text
	.global activa_bit

activa_bit:
	# prologo
	pushl	%ebp
	movl	%esp,%ebp
	pushl	%ebx

	movl	8(%ebp), %edx		# *n em edx
	movl	12(%ebp), %ecx		# posição do bit a activar em ecx


	# verificar se o bit vai ser alterado
	movl 	$1, %eax		# 1 em eax
	shl	%cl, %eax		# 1 << pos
	movl	%eax, %ebx		# ebx = (1<<pos) para o caso de ser preciso em baixo
	andl	(%edx), %eax		# *n & (1<<pos)
	shr	%cl, %eax		# deslocar resultado para o lado menos sign
	xorl	$1, %eax		# se o bit estava activado saltar pois nao preciso alterar
	je	fim


	orl	%ebx, (%edx)		# n |= (1 << pos)

fim:
	#epilogo
	popl	%ebx
	movl	%ebp,%esp
	popl	%ebp
	ret
