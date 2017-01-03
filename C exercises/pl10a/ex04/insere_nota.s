.section .data
.section .text
.global insere_notas
insere_notas:
	pushl %ebp   #Prologo
	movl %esp, %ebp
	pushl %ebx
	pushl %esi
	movl 8(%ebp),%ecx
	movl 12(%ebp),%edx
	movl $10,%eax #numero de notas 

aponta_notas:
	addl $8, %ecx 

altera:
	cmpl $0, %eax
	je fim
	movl (%edx),%ebx
	movl %ebx, (%ecx)
	addl $4 ,%edx
	addl $4, %ecx
	decl %eax
	jmp altera
	
fim:
	popl %esi
	popl %ebx #Epilogo
	movl %ebp, %esp
	popl %ebp
	ret
