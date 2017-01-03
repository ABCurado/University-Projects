.section .data
.section .text
.global insere_dados
insere_dados:
	pushl %ebp   #Prologo
	movl %esp, %ebp
	pushl %ebx
	pushl %esi
	movl 8(%ebp),%ecx
w:
	movl 12(%ebp), %eax
	movw (%eax), %bx
	movw %bx, (%ecx)
	addl $2, %eax
	addl $2 ,%ecx
	movw (%eax), %bx
	movw %bx, (%ecx)
	addl $2, %eax
	addl $2 ,%ecx
	movw (%eax), %bx
	movw %bx, (%ecx)
	addl $4 ,%ecx

j:
	movl 16(%ebp), %ebx
	movl %ebx, (%ecx)
	addl $4, %ecx
d:
	movl 20(%ebp), %eax
	movb (%eax), %bl
	movb %bl, (%ecx)
	incl %ecx
	incl %eax
	movb (%eax), %bl
	movb %bl, (%ecx)
	incl %ecx
	incl %eax
	movb (%eax), %bl
	movb %bl, (%ecx)


fim:
	popl %esi
	popl %ebx #Epilogo
	movl %ebp, %esp
	popl %ebp
	ret
