.section .data
	.global ptr1
	.global ptr2

.section .text
	.global str_copy_p

str_copy_p:
	movl	ptr1, %ebx	# guarda o addr do vetor de char em ebx
	movl	ptr2, %eax	# vetor vazio de destino
	movl	$0, %ecx

	ciclo:
		cmpb    $0, (%ebx)	# se '0' -> fim de string
                je      fim

		cmpb	$'b', (%ebx)	# se 'b' -> trocar por 'v'
		je	b_por_v

		movb	(%ebx), %dl	# Copiar o char para dl

		movb	%dl, (%eax)	# Guardar o char no destino

		incl	%ebx		# proxima posição
		incl	%eax
		jmp	ciclo

	b_por_v:
		movb	$'v', (%eax)	# guarda 'v' no destino
		incl	%ebx		# e avança de posição
		incl	%eax
		incl	%ecx
		jmp	ciclo

fim:
	movl	$0, (%eax)		#termina string destino com 0
	movl	%ecx, %eax		#retorna nr de chars alterados
	ret
