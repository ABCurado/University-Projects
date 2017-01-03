.section .data
	.global ptrvec
	.global num

.section .text
	.global vec_pos		#Devolve nr de elementos de um vetor maiores ou iguais a 10

vec_pos:
	movl	ptrvec,	%ebx	# endereço do vetor de inteiros em ebx
	movl	num, %ecx	# num de elementos em ecx
	movl	$0, %eax	# inicializar contador a zero
	jmp teste		# teste à cabeça do while()

ciclo:
	movl	(%ebx), %edx	# guardar elem em edx

	cmpl	$10, %edx
	jl 	incrementar	# Não é >= 10 : salta-se

	incl    %eax		# Sendo >= 10 conta-se

	incrementar:
		leal	4(%ebx), %ebx	# avançar 4 bytes(long int)
		decl	%ecx		# i--

teste:
	cmpl	$0, %ecx	# se num elementos = 0 ? fim senao : continua o ciclo
	jg	ciclo
	ret
