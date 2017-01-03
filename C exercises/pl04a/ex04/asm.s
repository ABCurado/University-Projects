.section .data
	.global op1
	.global op2
	.global res
	.section .text
	.global soma #int soma(void)
	.equ NUM, 10

soma:
	movl $NUM, %ebx #coloca o valor de op1 em ebx
	movl op1, %eax #coloca o valor de op2 em eax
	subl %ebx, %eax #soma o valor armazenado no ebx com eax e
	#guarda o valor da subtracao em eax (op1 - 10)

	movl op2, %ecx
	subl %ecx, %ebx # %ebx guarda (10 - op2)

	subl %ebx, %eax # %eax guarda (op1 - 10) - (10 - op2)

	movl %eax, res # copia para a variável res
	ret
