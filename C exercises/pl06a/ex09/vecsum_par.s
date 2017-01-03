.section .data
	.global ptrvec
	.global num

.section .text
	.global vecsum_par

vecsum_par:
	movl	ptrvec,	%ebx	# endereço do vetor de inteiros em ebx
	movl	num, %ecx	# num de elementos em ecx
	movl	$0, %eax	# inicializar soma a zero
	jmp teste		# teste à cabeça do while()

ciclo:
	movl	(%ebx), %edx	# guardar elem em edx

	andl	$1, %edx	# elem MOD 2 = 0 ? (Par?)
	cmpl	$0, %edx
	jne 	incrementar

	addl    (%ebx), %eax    # somar elemento par do vetor

	incrementar:
		leal	4(%ebx), %ebx	# avançar 4 bytes(int)
		decl	%ecx		# i--

teste:
	cmpl	$0, %ecx	# se num elementos = 0 ? fim senao : continua o ciclo
	jg	ciclo
	ret
