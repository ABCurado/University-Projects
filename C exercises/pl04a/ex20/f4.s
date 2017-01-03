.section .data      # identificador de secção
	.global i
	.global j
	.section .text
	.global f4

f4:
	movl i, %eax
	movl j, %ebx
	movl i, %ecx
	addl %ebx, %ecx
	movl $10, %edx
	cmpl %eax, %edx
	jg inferior_ou_igual
	je inferior_ou_igual

superior: 
	imull $2, %eax
	jmp fim


inferior_ou_igual:
	movl $0 , %edx
	movl %ebx, %eax
	movl $3, %ecx
	divl %ecx
	jmp fim


fim : 
	ret 
