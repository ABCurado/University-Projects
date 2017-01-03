.section .text
	.global somatorio

somatorio:

	# prologo
	pushl	%ebp
	movl	%esp,%ebp
	pushl	%ebx

	movl	$0, %eax
	movl	$0, %ebx
	movl	8(%ebp), %ecx
	jmp	teste

ciclo:
	incl	%ebx
	addl	%ebx, %eax

teste:
	cmpl	%ebx, %ecx
	je	fim
	jmp	ciclo

fim:
	#epilogo
	popl	%ebx
	movl	%ebp,%esp
	popl	%ebp

ret
