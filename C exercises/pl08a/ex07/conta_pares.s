.section .data
.section .text
.global conta_pares
conta_pares:
	pushl %ebp   #Prologo
	movl %esp, %ebp
	pushl %ebx
	movl 8(%ebp),%edx
	movl 12(%ebp),%ebx
	movl $0, %ecx
	movl $0, %eax
 
somar_par:
	cmpl %ebx, %ecx
	je fim
	testl $1, (%edx) #poe a flag referente a edx
	jz par #Caso a flag de paridade seja 1
	incl %ecx
	addl $4, %edx
	jmp somar_par

par: #adiciona o numero par a eax
	incl %eax
	addl $4, %edx
	incl %ecx
	jmp somar_par

fim:	
	popl %ebx #Epilogo
	movl %ebp, %esp
	popl %ebp
	ret
