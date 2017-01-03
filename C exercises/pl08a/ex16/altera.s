.section .text
	.global altera
	.global imprimeVetor

altera:
	# prologo
	pushl	%ebp
	movl	%esp,%ebp
	pushl	%ebx

	subl	$8, %esp
	movl	12(%ebp), %ecx		# tamanho do vec
	movl	8(%ebp), %edx		# addr do vec

	movl    $0xF, %ebx              # mask de extraccao dos bits a alterar
	shl     $8, %ebx
	movl    %ebx, -4(%ebp)

	notl    %ebx			# mask para desactivar bits do num
	movl	%ebx, -8(%ebp)

ciclo:
	cmpl	$0, %ecx	#teste a cabeca do while (cont > 0)
	je	fim

	movl	(%edx), %eax		# nr em eax
	movl	-4(%ebp), %ebx		# mask de extraccao
	andl	%eax, %ebx
	shr	$8, %ebx
	cmpl	$3, %ebx
	jg	subtrair_un
	addl	$4, %edx		# vec++
	decl	%ecx			# cont--
	jmp	ciclo

subtrair_un:
	subl	$1, %ebx		#
	shl	$8, %ebx		# bits - 1 un
	pushl	%ebx
	movl	-8(%ebp), %ebx		# mask para desactivar bits do num
	andl	%ebx, %eax		# desactivados os 4 bits menos sign do 2nd byte
	popl	%ebx
	orl	%ebx, %eax		# novoNum = num OR novos bits
	movl	%eax, (%edx)		# *vec = novoNum
	addl    $4, %edx		# vec++
	decl    %ecx			# cont--
	jmp     ciclo

fim:
	#epilogo
	popl	%ebx
	movl	%ebp,%esp
	popl	%ebp
	ret
