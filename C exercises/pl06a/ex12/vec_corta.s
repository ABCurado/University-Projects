.section .data
.global ptrvec
.global num
.global num_pretendido
.section .text
.global vec_corta 
vec_corta:
	movl $ptrvec, %edx #coloca o valor de op1 em ebx
	movl num, %ebx
	movl $0, %eax
	movl $0, %ecx
	jmp cortar
 
cortar:
	cmpl %ebx, %ecx
	je fim
	cmpl $1000, (%edx) #verifica se os 32 bits menos significativos sao inferiores a 1000
	jge superior
	jmp verifica	

verifica:		
	cmpl $0, 4(%edx) #caso sejam verifica se os mais significativos sao iguais 0
	jne superior
	incl %ecx 
	addl $8, %edx
	jmp cortar

superior: #caso seja superior poe os 64 bits a 0
	incl %eax
	movl $0,(%edx) #zerar os primeiros 32 bits (menos significativos)
	movl $0, 4(%edx) #zerar os segundos 32 bits (mais significativos)
	incl %ecx 
	addl $8, %edx
	jmp cortar
fim:	
	ret
