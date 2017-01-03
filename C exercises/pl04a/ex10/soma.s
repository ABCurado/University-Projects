.section .data
	.global op1
	.global op2
	.global res
	.global OF
	.global CF
	.section .text
	.global soma

soma:
	movl op1, %eax
	movl op2, %ebx
	addl %ebx, %eax		#soma o valor armazenado em ebx com eax
	jo output_com_overflow 	# jump if overflow
	jc output_com_carry # jump if carry
	jmp fim

output_com_carry: #
	movl $1, CF #Altera a flag de carry na memoria
	jmp fim

output_com_overflow:
	movl $1, OF #Altera a flag de overflow na memoria
	jc output_com_carry

fim:
	movl %eax, res		#copia para a variável res
	ret
