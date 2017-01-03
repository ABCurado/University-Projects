.section .data
	.global A
	.global B
	.global C
	.global D
	.global res
	.section .text
	.global operacoes

operacoes:
	movb A, %al
	cbw
	cwde
	
	movw B, %bx
	movsx %bx, %ebx
	addl %ebx, %eax		#soma A e B
	
	movl C, %ebx
	subl %ebx, %eax #subtrai ao resultado C 
	
	movl D, %edx #soma o resultado com D
	addl %edx, %eax	

	jo output_com_overflow #Caso tenha acontecido overflow
	movl $0, %edx
	jmp fim

output_com_overflow:
	movl $1, %edx

fim:
	movl %eax, res
	movl %edx, res+4
	ret
