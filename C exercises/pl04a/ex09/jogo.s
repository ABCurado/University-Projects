.section .data
	.global x

.section .text
	.global jogo

jogo:
	movl x, %ebx
	subl $1, %ebx		# n=n-1
	imull $3, %ebx, %eax	# n=3*n
	addl $12, %eax		# n=n+12

	movl $0, %ebx
	movl $0, %ecx
	movl $0, %edx
	cdq
	movl $3, %ebx
	idivl %ebx		# n=n/3

	addl $5, %eax
	subl x, %eax

	ret
