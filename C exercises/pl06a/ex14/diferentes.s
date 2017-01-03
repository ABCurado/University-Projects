.section .data
.global ptrvec1
.global ptrvec2
.global ptrvec3
.global num
.section .text
.global diferentes 
diferentes:
	movl $ptrvec1, %esi #coloca o apontador do primeiro vetor em esi
	movl $ptrvec2, %edi #coloca o apontador do segundo vetor em edi
	movl $ptrvec3, %ebx #coloca o apontador do vetor resultado em ebx
	movl $0, %eax #contador de ococrrencias
	movl $-1, %edx #iterador de ciclos
	movl $0, %ecx 
	push %edx
	jmp correr_ptr1

correr_ptr1:
	pop %edx #retira da stack o valor do apontador de edx
	cmpl num, %edx
	je fim
	incl %edx
	movw (%esi,%edx,2), %cx #coloca o valor em cx
	push %edx
	movl $0, %edx
	jmp correr_ptr2

correr_ptr2: #itera o segundo vetor para verificar que cx nao existe no vetor 2
	cmpl num , %edx
	je adiciona
	cmpw (%edi,%edx,2), %cx
	je correr_ptr1 #caso encontre cx no vetor 2 volta para o ciclo de correr_ptr1
	incl %edx
	jmp correr_ptr2

adiciona: #adiciona ao vetor de resultados cx e incrementa ao contador
	movl $0, (%ebx)
	movw %cx, (%ebx)
	incl %eax
	addl $4, %ebx
	jmp correr_ptr1
fim:	
	ret
