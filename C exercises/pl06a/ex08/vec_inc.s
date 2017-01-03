.section .data
.global ptrvec
.global num
.section .text
.global vec_inc 
vec_inc:
	movl $ptrvec, %edx #coloca o valor de op1 em ebx
	movl num, %ebx
	movl $0, %ecx
	movl $0, %eax
	jmp somar_vetor
 
somar_vetor: #incrementa 1 a cada posição do vetor
	cmpl %ecx, %ebx
	je fim
	incl (%edx)
	addl $4, %edx
	incl %ecx
	jmp somar_vetor


fim:	
	ret
