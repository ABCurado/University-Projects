.section .data
	.global ptrvec
	.global num

.section .text
	.global vec_sum

vec_sum:
	movl	ptrvec,	%ebx	# endereço do vetor de inteiros em ebx
	movl	num, %ecx	# num de elementos em ecx
	movl	$0, %eax	# inicializar soma a zero
	jmp teste		# teste à cabeça do while()

ciclo:
	addl	(%ebx), %eax	# somar elemento do vetor
	leal	4(%ebx), %ebx	# avançar 4 bytes(int)
	decl	%ecx		# i--

teste:
	cmpl	$0, %ecx	# se num elementos = 0 ? fim senao : continua o ciclo
	jg	ciclo
	ret
