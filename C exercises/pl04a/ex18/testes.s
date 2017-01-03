.section .data
	.global x
	.global res

.section .text
	.global testes

testes:
	movl	$1, %eax	# À partida o num é par e positivo (1)
	movl	$0, %edx

	movl	x, %ecx
	cmpl	$0, %ecx
	jl	negativo
	jmp	par_impar

negativo:
	addl	$1, %eax	# Se for par e negativo +1 (2)
	jmp	par_impar

par_impar:
        andl    $1, %ecx        # 1 se for ímpar, 0 se par em ecx
        imull    $2, %ecx
	addl	%ecx, %eax	#Se ímpar eax+=2, senao eax+=0
				#Ou seja, se era 1->3; se era 2->4

fim:
	movl	%eax, res
	ret
