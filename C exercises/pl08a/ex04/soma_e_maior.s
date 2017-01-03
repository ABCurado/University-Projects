.section .text
	.global soma_e_maior

soma_e_maior:
	# prologo
	pushl	%ebp
	movl	%esp,%ebp
	pushl	%ebx

	movl	16(%ebp), %edx		# 3º param (*maior)
	movl	12(%ebp), %ebx		# 2º param
	movl    8(%ebp), %eax           # 1º param

	movl	%ebx, %ecx		# assumir que 2º param é o maior
					#(testado mais abaixo) guardando-o em ecx

	cmpl	%eax, %ebx
	cmovl	%eax, %ecx		# se 1º param > 2º param : *maior = 1ºparam

	movl	%ecx, (%edx)		# guardar o maior em *maior
	addl	%ebx, %eax		# return 1º+2º param

fim:
	#epilogo
	popl	%ebx
	movl	%ebp,%esp
	popl	%ebp
	ret
