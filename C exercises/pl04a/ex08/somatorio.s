.section .data
	.global n

.section .text
	.global somatorio

somatorio:
	movl n, %ebx		# inicializar %bx com n
	movl $1, %ecx		# inicializar %cx a 1 (i)
	movl $0, %eax		#inicializar resultado a zero

	cmpl $0, %ebx		# se n==0 sair, senão continuar
	je end			# com o somatorio
	jg soma

soma:
	addl %ecx, %eax		# resultado += i
	addl $1, %eax		# resultado += 1 ( resultado += (i+1) )
	inc %ecx		# i++
	dec %ebx		# n--

	cmpl $0, %ebx		#Se n==0 sair senão continuar
	je end
	jg soma

end:
	ret
