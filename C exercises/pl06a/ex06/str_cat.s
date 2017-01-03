.section .data
.global ptr1
.global ptr2
.global ptr3
.section .text
.global str_cat 
str_cat:
	movl $ptr1, %eax #primeira string em eax
	movl $ptr2, %ebx #segunda string em edx
	movl $ptr3, %ecx #string resultado em ecx
	jmp concatena_ptr1
 
concatena_ptr1:
	cmpb $0, (%eax)
	je concatena_ptr2 #acaba caso a primeira string tenha chegado ao fim nao adicionando o =0
	movb (%eax), %dl #copia a primeira string para o resultado
	movb %dl, (%ecx)
	incl %eax
	incl %ecx
	jmp concatena_ptr1

concatena_ptr2:
	movb (%ebx), %dl #copia a segunda string para o resultado
	movb %dl, (%ecx)
	incl %ebx
	incl %ecx
	cmpb $0, (%ebx)
	je fim  #acaba caso a segunda string tenha chegado ao fim adicionando o 0
	jmp concatena_ptr2

fim:	
	ret
