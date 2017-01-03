.section .data
	.global count
	.global ptr1

.section .text
	.global space_count

space_count:
	movl	ptr1, %ebx	# guarda o addr do vetor de char em ebx
	movl	$0, %ecx	# incicializa em 0 o contador ecx

	ciclo:
		cmpb    $0, (%ebx)       # se '0' -> fim de string
                je      fim

		cmpb	$' ', (%ebx)	# se ' ' -> contar espaço
		je	espaco

		incl	%ebx
		jmp	ciclo

	espaco:
		incl	%ecx
		incl	%ebx
		jmp	ciclo

	fim:
		movl	%ecx, %eax

ret
