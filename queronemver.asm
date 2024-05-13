global main
extern printf
extern scanf
section .text
main: 	   ;Entrada do Programa
	   push ebp
	   mov ebp, esp
	sub esp, 0

	mov edx, ebp
	lea eax, [edx - 0]
	push eax
	push @Integer
	call scanf
	add esp, 8
	mov edx, ebp
	lea eax, [edx - 4]
	push eax
	push @Integer
	call scanf
	add esp, 8
	mov edx, ebp
	lea eax, [edx - 0]
	push eax
	push @Integer
	call scanf
	add esp, 8
	mov edx, ebp
	lea eax, [edx - 4]
	push eax
	push @Integer
	call scanf
	add esp, 8
	push dword[ebp - 0]
	push @Integer
	call printf
	add esp, 8
	push dword[ebp - 0]
	push @Integer
	call printf
	add esp, 8
	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	push 1
rotuloRepeat1: 	push dword[ebp - 0]
	push @Integer
	call printf
	add esp, 8
	push dword[ebp - 0]
	push 1
	pop eax
	add dword[ESP], eax
	push dword[ebp - 0]
	push 10
	pop eax
	cmp dword [ESP], eax
	jle rotuloFalsoREL2
	mov dword [ESP], 1
	jmp rotuloSaidaREL3
rotuloFalsoREL2: 	mov dword [ESP], 0
rotuloSaidaREL3: 	cmp dword[esp], 0

	jerotuloRepeat1
	push dword[ebp - 0]
	push 1
	pop eax
	cmp dword [ESP], eax
	jl rotuloFalsoREL4
	mov dword [ESP], 1
	jmp rotuloSaidaREL5
rotuloFalsoREL4: 	mov dword [ESP], 0
rotuloSaidaREL5: 	push dword[ebp - 0]
	push @Integer
	call printf
	add esp, 8
	push dword[ebp - 0]
	push 1
	pop eax
	sub dword[ESP], eax
	push dword[ebp - 0]
	push 10
	pop eax
	cmp dword [ESP], eax
	jl rotuloFalsoREL6
	mov dword [ESP], 1
	jmp rotuloSaidaREL7
rotuloFalsoREL6: 	mov dword [ESP], 0
rotuloSaidaREL7: 	push dword[ebp - 0]
	push @Integer
	call printf
	add esp, 8
	push dword[ebp - 4]
	push @Integer
	call printf
	add esp, 8
	push dword[ebp - 0]
	push 10
	pop eax
	cmp dword [ESP], eax
	jl rotuloFalsoREL8
	mov dword [ESP], 1
	jmp rotuloSaidaREL9
rotuloFalsoREL8: 	mov dword [ESP], 0
rotuloSaidaREL9: 	push dword[ebp - 0]
	push @Integer
	call printf
	add esp, 8
	push dword[ebp - 4]
	push @Integer
	call printf
	add esp, 8
	push dword[ebp - 4]
	push @Integer
	call printf
	add esp, 8
	push dword[ebp - 0]
	push @Integer
	call printf
	add esp, 8
	push dword[ebp - 0]
	push 10
	pop eax
	cmp dword [ESP], eax
	jl rotuloFalsoREL10
	mov dword [ESP], 1
	jmp rotuloSaidaREL11
rotuloFalsoREL10: 	mov dword [ESP], 0
rotuloSaidaREL11: 	push dword[ebp - 4]
	push 10
	pop eax
	cmp dword [ESP], eax
	jg rotuloFalsoREL12
	mov dword [ESP], 1
	jmp rotuloSaidaREL13
rotuloFalsoREL12: 	mov dword [ESP], 0
rotuloSaidaREL13: 	cmp dword [ESP + 4], 1
	jne rotuloFalsoMTL15
	cmp dword [ESP + 4], dword [ESP]
	jne rotuloFalsoMTL15
	mov dword [ESP + 4], 1
	jmp rotuloSaidaMTL14
rotuloFalsoMTL15: 	mov dword [ESP + 4], 0
rotuloSaidaMTL14: 	add esp, 4
	push dword[ebp - 0]
	push @Integer
	call printf
	add esp, 8
	push dword[ebp - 4]
	push @Integer
	call printf
	add esp, 8
	push dword[ebp - 0]
	push 10
	pop eax
	cmp dword [ESP], eax
	jl rotuloFalsoREL16
	mov dword [ESP], 1
	jmp rotuloSaidaREL17
rotuloFalsoREL16: 	mov dword [ESP], 0
rotuloSaidaREL17: 	push dword[ebp - 4]
	push 10
	pop eax
	cmp dword [ESP], eax
	jg rotuloFalsoREL18
	mov dword [ESP], 1
	jmp rotuloSaidaREL19
rotuloFalsoREL18: 	mov dword [ESP], 0
rotuloSaidaREL19: 	cmp dword [ESP + 4], 1
	je rotuloVerdadeMEL21
	cmp dword [ESP], 1
	je rotuloVerdadeMEL21
	mov dword [ESP + 4], 0
	jmp rotuloSaidaMEL20
rotuloVerdadeMEL21: 	mov dword [ESP + 4], 1
rotuloSaidaMEL20: 	add esp, 4
	push dword[ebp - 0]
	push @Integer
	call printf
	add esp, 8
	push dword[ebp - 4]
	push @Integer
	call printf
	add esp, 8
	push dword[ebp - 0]
	push 10
	pop eax
	cmp dword [ESP], eax
	jl rotuloFalsoREL22
	mov dword [ESP], 1
	jmp rotuloSaidaREL23
rotuloFalsoREL22: 	mov dword [ESP], 0
rotuloSaidaREL23: 	cmp dword [ESP], 1
	jne rotuloFalsoFL24
	mov dword [ESP], 0
	jmp rotuloSaidaFL25
rotuloFalsoFL24: 	mov dword [ESP], 1
rotuloSaidaFL25: 	push dword[ebp - 0]
	push @Integer
	call printf
	add esp, 8
	push dword[ebp - 4]
	push @Integer
	call printf
	add esp, 8
	push 10
	leave
	ret

section .data

@Integer: db '%d',0
@IntegerLN: db '%d',10,0
rotuloStringLN: db '' , 10,0
