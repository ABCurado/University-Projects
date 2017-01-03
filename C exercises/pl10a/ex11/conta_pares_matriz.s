.section .text
	.global conta_pares_matriz		# int conta_pares_matriz(int **m, int y, int k)

conta_pares_matriz:
	# prologo
	pushl	%ebp
	movl	%esp,%ebp
	pushl	%ebx

	movl	8(%ebp), %edx		# apontador para matriz
	movl	(%edx), %ebx		# guardar em ebx inicio do espaço de memoria contendo os apontadores das linhas
	movl	$0, %ecx		# contador para cada linha
	movl	$0, %edx		# contador para cada coluna
	movl	$0, %eax		# var de retorno inicializada a zero (nr de pares encontrados)

ciclo1:
	cmpl	12(%ebp), %ecx		# teste do while (cont < nr linhas)
	je	fim

	ciclo2:
		cmpl    16(%ebp), %edx		# teste while (cont2 < nr colunas)
		je	fim_ciclo2
		movl    (%ebx,%edx,4), %esi	# n-ário elem da linha em esi
		call	teste_par
		incl	%edx
		jmp	ciclo2

	fim_ciclo2:
	movl	$0, %edx
	incl	%ecx
	movl	8(%ebp), %ebx	# proxima linha da matriz
	movl	(%ebx,%ecx,4), %ebx
	jmp	ciclo1

teste_par:
	test	$1, %esi		# Elem AND 1 = ?
	jz	par_encontrado
	ret

par_encontrado:
	incl	%eax
	ret

fim:
	#epilogo
	popl	%ebx
	movl	%ebp,%esp
	popl	%ebp
	ret
