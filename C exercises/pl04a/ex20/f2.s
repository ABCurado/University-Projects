.section .data      # identificador de secção
	.global i
	.global j
	.section .text
	.global f2

f2:
	movl i, %eax
	movl j, %edx
	cmpl %eax, %edx
	jl inferior
	
superior_ou_igual:
	incl  %eax
	jmp fim

inferior: 
	incl %edx
	jmp fim

fim:
	imull %edx, %eax
	ret
