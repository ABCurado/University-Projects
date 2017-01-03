.section .data
.section .text
.global  quadrado
 quadrado:
	pushl %ebp   #Prologo
	movl %esp, %ebp
	pushl %ebx
 	movl 8(%ebp), %eax

conta:
	imull 8(%ebp), %eax

	
fim:	
	popl %ebx 
	movl %ebp, %esp
	popl %ebp
	ret
