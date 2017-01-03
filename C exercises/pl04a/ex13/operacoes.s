.section .data
	.global A
	.global B
	.global C
	.global D
	.global res
	.section .text
	.global operacoes

operacoes:
	movl B, %eax
	movl C, %ebx

	imull %ebx, %eax		#Multiplica B e C
	
	movl D, %ebx
	movl $0, %edx #Zera o registo %edx pois a divisao é feita por eax:%edx
	divl %ebx  #divide o resultado por D
	
	movl A, %ebx 
	addl %ebx, %eax	#adiciona o resultado a A
	jc output_com_carry
	movl $0, %edx
	jmp fim

output_com_carry:
	movl $1, %edx

fim:
	movl %eax, res
	movl %edx, res+4
	ret
