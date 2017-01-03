.section .data
	.global temp_atual
	.global temp_pretendida
	.global temp
	.section .text
	.global QuenteFrio

QuenteFrio:
	movw temp_atual, %ax
	movw temp_pretendida, %dx
	movw $0, %cx
	cmpw %dx, %ax
	jg descer #Caso a tempratura atual seja maior que a pretendida desce
	jl subir #Caso a tempratura atual seja menor que a pretendida sobe
	je fim  #Caso a temp pretendida seja igual á atual retorna com 0

descer: 
	decw %ax
	addw $2, %cx
	cmpw %ax, %dx
	jl descer
	jg fim
	je fim

subir: 
	incw %ax
	addw $3, %cx
	cmpw %ax, %dx
	jl fim
	je fim
	jg subir

fim:
	imulw $60, %cx
	movw %cx, temp
	ret
