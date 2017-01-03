.section .data
	.global op1
	.global op2
	.global res
	.global resto

.section .text
	.global divisao

divisao:
	movl	op1, %eax	#
	cdq			# Dividendo em edx:eax

	movl	op2, %ebx	# divisor em ebx

	idivl	%ebx		# edx:eax / ebx - resultado-eax

	movl	%edx, resto	# edx para resto
	movl	%eax, res	# eax para resultado (res)
	ret
