.section .data
	.global ptr1
	.global ptr2

.section .text
	.global str_copy_p2

str_copy_p2:
	movl	ptr1, %ebx	# guarda o addr do vetor de char em ebx
	movl	ptr2, %eax	# vetor vazio de destino
	movl	$0, %ecx	# contador de alterações

	ciclo:
		cmpb    $0, (%ebx)	# se '0' -> fim de string
                je      fim
		cmpb	$'b', (%ebx)	# se 'b' -> trocar por 'v'
		je	b_por_v
		cmpb    $'v', (%ebx)    # se 'v' -> trocar por 'b'
                je      v_por_b

		movb	(%ebx), %dl	# Copiar o char para dl
		movb	%dl, (%eax)	# Guardar o char no destino
		incl	%ebx		# proxima posição
		incl	%eax
		jmp	ciclo

	b_por_v:
		movb	$'v', (%eax)	# guarda 'v' no destino
		call    incrementa
		jmp	ciclo

	v_por_b:
		movb    $'b', (%eax)    # guarda 'b' no destino
		call	incrementa
                jmp     ciclo

	incrementa:
		incl	%eax
		incl	%ebx
		incl	%ecx
		ret

fim:
	movl	$0, (%eax)		#termina string destino com 0
	movl	%ecx, %eax		# retorno do contador
	ret
