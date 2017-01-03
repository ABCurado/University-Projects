.section .data
.section .text
.global incrementa_e_quadrado
incrementa_e_quadrado:
	pushl %ebp   #Prologo
	movl %esp, %ebp
	pushl %ebx
	movl 8(%ebp),%ecx
	movl 12(%ebp),%edx

incrementa:
	incl (%ecx)
	push %edx
	call quadrado

fim:	
	popl %ebx #Epilogo
	movl %ebp, %esp
	popl %ebp
	ret
