.section .data
.section .text
.global calc
calc:
	pushl %ebp   #Prologo
	movl %esp, %ebp
	pushl %ebx
	movl 8(%ebp),%edx
	movl 12(%ebp),%ebx
	movl 16(%ebp),%eax
 
calculo:
	movl (%edx), %ecx
	subl %ebx, %ecx
	imull %ecx,%eax

fim:	
	popl %ebx #Epilogo
	movl %ebp, %esp
	popl %ebp
	ret
