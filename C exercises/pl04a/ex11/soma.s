.section .data
	.global op1
	.global op2
	.global res
	.section .text
	.global soma

soma:
	movl op1, %eax
	movl op2, %edx
	adcl %edx, %eax		#soma o valor armazenado em ebx com eax
	jc output_com_carry #caso aconteca overflow
	movl $0, %edx
	jmp fim

output_com_carry:
	movl $1, %edx # adiciona o extra ao registo edx

fim:
	movl %eax, res
	movl %edx, res+4
	ret
