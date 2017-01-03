.section .data      # identificador de secção
	.global i
	.global j
	.section .text
	.global f3

f3:
	movl i, %eax
	movl j, %ebx
	movl i, %ecx
	cmpl %eax, %ebx
	jl superior_ou_igual
	
superior_ou_igual: 
	addl %ebx, %eax
	incl %ebx
	jmp fim

inferior:
	imull %ebx, %eax
	addl %ecx,%ebx
	addl $2, %ebx
	jmp fim

fim:
	movl $0, %edx
	divl %ebx
	ret
