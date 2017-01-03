.section .data
	.global ptrvec
	.global num

.section .text
	.global preenche_vec	# troca um elemento negativo pelo seu indice

preenche_vec:
	movl	ptrvec,	%ebx	# endereço do vetor de inteiros em ebx
	movl	$0, %ecx	# inicializar indice
	movl	$0, %eax	# e contador de alteracoes a 0
	jmp teste		# teste à cabeça do while()

ciclo:
	cmpl	$0, (%ebx)
	jge 	incrementar	# Não é negativo : salta-se

	movl	%ecx, (%ebx)	# substitui-se pelo indice no vetor
	incl	%eax		# cont++

	incrementar:
		leal	4(%ebx), %ebx	# avançar 4 bytes(long int)
		incl	%ecx		# i++

teste:
	cmpl	num, %ecx	# se ecx < num reiterar
	jl	ciclo
	ret
