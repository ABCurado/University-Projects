.section .data
.section .text
.global menor_data
menor_data:
	pushl %ebp   #Prologo
	movl %esp, %ebp
	pushl %ebx
	pushl %esi
	movl 8(%ebp),%ecx
	movl 12(%ebp),%edx

aplica_mascara:
	ror $16, %ecx #Poe o ano nos bytes mais significavos para ser possivel compara-los 
	ror $16, %edx
	cmpl %ecx,%edx
	jg a_menor
b_menor:
	movl 12(%ebp), %eax
	jmp fim
a_menor:
	movl 8(%ebp), %eax
	
fim:
	popl %esi
	popl %ebx #Epilogo
	movl %ebp, %esp
	popl %ebp
	ret
