.section .data
	.global i
	.global n
	.global A
	.global B
	.global res
	.section .text
	.global operacoes

operacoes:
	movl A, %eax
	movl $0, %edx
	movl B, %ebx
	divl %ebx
	movl i, %ecx
	
somatorio:
	movl %ecx, %ebx
	imull %eax,%ebx 
	addl %ebx,%edx	
	incl %ecx
	cmpl %ecx, n
	jl fim
	je somatorio
	jg somatorio

fim:
	movl %edx, res
	ret
