.section .text
	.global junta_bits
	.global intBin

junta_bits:
	# prologo
	pushl	%ebp
	movl	%esp,%ebp
	pushl	%ebx
	subl	$4, %esp		# espaco para variavel aux

	movl	16(%ebp), %ecx		# pos em edx

	#mask de a
	movl	$-1, %edx
	shl	%cl, %edx

	#mask de b
	movl	%edx, %ebx
	notl	%ebx

	# aplicar mask de a
	movl	8(%ebp), %eax
	andl	%edx, %eax
	pushl	%eax
	call	intBin
	popl	%eax
	movl	%eax, -4(%ebp)		# guardar (a & maskA) na var auxiliar

	# aplicar mmask de b
	movl	12(%ebp), %eax
	andl	%ebx, %eax
	pushl   %eax
	call    intBin
	popl    %eax

	# concatenar com OR
	orl	-4(%ebp), %eax
	pushl   %eax
	call    intBin
	popl    %eax

fim:
	#epilogo
	popl	%ebx
	movl	%ebp,%esp
	popl	%ebp
	ret
