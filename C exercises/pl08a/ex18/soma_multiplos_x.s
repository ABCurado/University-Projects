.section .text
	.global soma_multiplos_x

soma_multiplos_x:
	pushl %ebp   #Prologo
	movl %esp, %ebp
	pushl %ebx
	pushl %esi
	movl 8(%ebp),%edx #vetor 1 para %edx
	movl 12(%ebp),%ebx #x para %ebx
	movl $0,%eax
	pushl %eax
	shrl $16, %ebx #Poe o 3º byte no 1º

ciclo:
	cmpb $0, (%edx) #verifica se o vetor chegou ao fim
	je fim
	movl $0,%eax
	movb (%edx), %al #Guarda o valor do apontado por edx
	idivb %bl 		#divide-o pelo 3º byte de x agora em bl
	cmpb $0, %ah	#Caso o resto seja 0 adiciona ao retorno
	je multiplo
	incl %edx
	jmp ciclo

multiplo:
	popl %eax
	movl $0, %ecx
	movb (%edx),%cl
	addl %ecx,%eax
	pushl %eax
	incl %edx
	jmp ciclo
fim:
	popl %eax
	popl %esi
	popl %ebx #Epilogo
	movl %ebp, %esp
	popl %ebp
	ret
