.section .data
.section .text
.global activa_esquerda_direita
activa_esquerda_direita:
	pushl %ebp   #Prologo
	movl %esp, %ebp
	pushl %ebx
	pushl %esi
	movl 8(%ebp),%eax
	movl 12(%ebp),%ecx
	movl 16(%ebp), %ebx
	movl $0,%edx
	cmpl %ecx, %edx
	jge fim
ciclo: #Cria uma mascara com os bits entre esq e direita 
	cmpl %ecx, %ebx
	jl fim
	movl $1, %esi
	shl %cl, %esi
	or %esi, %edx
	incl %ecx
	jmp ciclo
fim:	#Inverte a mascara e aplica-a ao numero
	not %edx
	or %edx, %eax
	movl %edx,%eax
	popl %esi
	popl %ebx #Epilogo
	movl %ebp, %esp
	popl %ebp
	ret
