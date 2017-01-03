.section .data
	.global valor
	valor:     # nome da variavel
		.int 302 
	.global res
.section .text
.global soma    #int  soma(void)
soma:
	movl valor, %eax #coloca o valor de op1 em ebx
	addb %ah, %al #coloca o valor de op1 em ebx
	movl $0, res
	movb %al, res
	ret
