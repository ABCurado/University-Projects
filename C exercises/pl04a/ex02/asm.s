.section .data
.global op1
.global op2
.global res
.section .text
.global soma #int soma(void)

soma:
	movl op1, %ebx #coloca o valor de op1 em ebx
	movl op2, %eax #coloca o valor de op2 em eax
	addl %ebx, %eax #soma o valor armazenado no ebx com eax e
	#guarda o valor da soma em eax

	movl %eax, res # copia para a variável res
	ret
