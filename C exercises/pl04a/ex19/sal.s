.section .data
	.equ AUM_OMISSAO,	50
	.equ AUM_GERENTE,	100
	.equ AUM_ENGENHEIRO,	200
	.equ AUM_TECNICO,	150

	.equ COD_GER,		101
	.equ COD_ENG,		102
	.equ COD_TEC,		103

	.global salario
	.global codigo
	.global res

.section .text
	.global sal

sal:
	movl    salario, %eax
	movl	codigo, %ebx
	cmpl	$COD_GER, %ebx
	je	gerente
	cmpl    $COD_ENG, %ebx
	je	engenheiro
	cmpl    $COD_TEC, %ebx
	je	tecnico
	jmp	omissao

gerente:
	addl	$AUM_GERENTE, %eax
	jmp	fim

engenheiro:
	addl	$AUM_ENGENHEIRO, %eax
        jmp     fim

tecnico:
	addl	$AUM_TECNICO, %eax
        jmp     fim

omissao:
	addl	$AUM_OMISSAO, %eax

fim:
	movl	%eax, res
	ret
