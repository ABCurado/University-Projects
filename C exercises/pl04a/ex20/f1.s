.section .data      # identificador de secção
	.global i
	.global j
	.section .text
	.global f1

f1:
	movl i, %eax
	movl j, %edx
	cmpl %eax, %edx
	je igual
	jne diferente
	
igual:
	subl %edx, %eax
	jmp fim

diferente:
	addl %edx, %eax

fim:
	ret
