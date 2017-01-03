.section .data
.section .text
.global somabyte2
somabyte2:
	pushl %ebp   #Prologo
	movl %esp, %ebp
	pushl %ebx
	pushl %esi
	movl 12(%ebp),%edx #vetor 1 para %edx
	movl 16(%ebp),%ebx #vetor 2 para %ebx
	movl (%edx) ,%eax
	movl %eax,(%ebx) #Copia o primeiro numeropois é o indicador de tamanho
	movl %eax, %ecx #guarda o tamanho em %ecx
	addl $4, %ebx #Salta para a posição seguinte
	addl $4, %edx
	movl $0,%eax
ciclo:
	cmpl $0, %ecx
	je fim
	movb 1(%edx), %al #move o segundo byte para %al
	addl 8(%ebp), %eax #adiciona x
	roll $8, %eax #poe o byte na segunda posição menos significativa
	movl (%edx), %esi
	and $0xFFFF00FF, %esi #poe o byte 1 a zeros
	or %eax, %esi	#junta o byte pretendido e o numero
	movl %esi,(%ebx) #move-o para o vetor 
	addl $4, %ebx
	addl $4, %edx
	movl $0,%eax
	decl %ecx
	jmp ciclo
	
fim:
	popl %esi
	popl %ebx #Epilogo
	movl %ebp, %esp
	popl %ebp
	ret
