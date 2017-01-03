.section .data
.global ptrvec1
.global ptrvec2
.global ptrvec3
.global num
.section .text
.global comuns 
comuns:
	movl $ptrvec1, %esi #coloca o apontador do primeiro vetor em esi
	movl $ptrvec2, %edi #coloca o apontador do segundo vetor em edi
	movl $ptrvec3, %ebx #coloca o apontador do vetor resultado em ebx
	movl $0, %eax #contador de ococrrencias
	movl $-1, %edx #iterador de ciclos
	movl $0, %ecx 
	push %edx
	jmp correr_ptr1

correr_ptr1: #corre o primeiro vetor
	pop %edx #retira da stack o valor do apontador de edx
	cmpl num, %edx
	je fim
	incl %edx
	push %edx
	movl (%esi,%edx,4), %ecx #coloca o valor em ecx
	movl $0, %edx
	jmp correr_ptr2

correr_ptr2:	 #itera o segundo vetor para verificar se ecx existe no vetor 2
	cmpl num , %edx
	je correr_ptr1 #caso percorra o vetor 2 todo e nao encontre volta ao vetor 1
	cmpl (%edi,%edx,4), %ecx
	je comum #caso encontre cx no vetor 2 volta verifica se este nao esta contido no vetor de resultado 
	incl %edx
	jmp correr_ptr2

comum:
	movl $0, %edx #coloca o iterador a 0

correr_ptr3: # verifica que ecx nao se encontra repetido no vetor 3
	cmpl num , %edx
	je adiciona
	cmpl (%ebx,%edx,4), %ecx
	je correr_ptr1
	incl %edx
	jmp correr_ptr3

adiciona:
	movl %ecx, (%ebx,%eax,4)
	incl %eax
	jmp correr_ptr1
fim:	
	ret
