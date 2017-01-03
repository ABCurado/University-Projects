.section .data
	.global ptr1

.section .text
	.global decifra

decifra:
	movl	ptr1, %ebx	# guarda o addr do vetor de char em ebx
	movl	$0, %ecx	# incicializa em 0 o contador ecx

	ciclo:
		cmpb    $0, (%ebx)       # se 0 -> fim de string
		je      fim

		cmpb	$' ', (%ebx)	# se ' ' -> nao se altera
		je	avanca_sem_alterar

		cmpb    $'0', (%ebx)    # se '0' -> nao se altera
		je      avanca_sem_alterar

		subb	$1, (%ebx)
		incl	%ebx
		incl	%ecx
		jmp	ciclo

	avanca_sem_alterar:
		incl	%ebx
		jmp	ciclo

	fim:
		movl	%ecx, %eax

ret
