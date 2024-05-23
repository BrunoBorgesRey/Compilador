 : global main
extern printf
extern scanf
section .text
main: 	   ;Entrada do Programa
	   push ebp
	   mov ebp, esp
	sub esp, 0

	sub esp, 0

	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	mov edx, ebp
	lea eax, [edx - 0]
	push eax
	push @Integer
	call scanf
	add esp, 8
	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
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
	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	push 1
	pop dword[ebp - 0]
rotuloFOR1: 	push 10
	push ecx
	mov ecx, dword[ebp - 0]
	cmp ecx, dword[esp+4]
	jg rotuloFIMFOR2
	pop ecx
	push dword[ebp - 0]
	push @Integer
	call printf
	add esp, 8
	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	add dword[ebp - 0], 1
	jmp rotuloFOR1
rotuloFIMFOR2: 	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	push 1
	pop eax
	mov dword[ebp - 0], eax
rotuloRepeat3: 	push dword[ebp - 0]
	push @Integer
	call printf
	add esp, 8
	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	push dword[ebp - 0]
	push 1
	pop eax
	add dword[ESP], eax
	pop ecx
	push eax
	pop eax
	mov dword[ebp - 0], eax
	push dword[ebp - 0]
	push 10
	pop eax
	cmp dword [ESP], eax
	jle rotuloFalsoREL4
	mov dword [ESP], 1
	jmp rotuloSaidaREL5
rotuloFalsoREL4: 	mov dword [ESP], 0
rotuloSaidaREL5: 	cmp dword[esp], 0
	jerotuloRepeat3
	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
rotuloWhile6: 	push dword[ebp - 0]
	push 1
	pop eax
	cmp dword [ESP], eax
	jl rotuloFalsoREL8
	mov dword [ESP], 1
	jmp rotuloSaidaREL9
rotuloFalsoREL8: 	mov dword [ESP], 0
rotuloSaidaREL9: 	cmp dword[esp], 0

	je rotuloFimWhile7
	push dword[ebp - 0]
	push @Integer
	call printf
	add esp, 8
	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	push dword[ebp - 0]
	push 1
	pop eax
	sub dword[ESP], eax
	pop eax
	mov dword[ebp - 0], eax
	jmp rotuloWhile6
rotuloFimWhile7: 	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	push 10
	pop eax
	mov dword[ebp - 0], eax
	push dword[ebp - 0]
	push 10
	pop eax
	cmp dword [ESP], eax
	jl rotuloFalsoREL10
	mov dword [ESP], 1
	jmp rotuloSaidaREL11
rotuloFalsoREL10: 	mov dword [ESP], 0
rotuloSaidaREL11: 	cmp dword[esp], 0

	je rotuloElse12
rotuloFimIf13: 	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	jmp rotuloFimIf13
null:
rotuloFimIf13: 	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	push 1
	pop eax
	mov dword[ebp - 0], eax
	push dword[ebp - 0]
	push 10
	pop eax
	cmp dword [ESP], eax
	jl rotuloFalsoREL14
	mov dword [ESP], 1
	jmp rotuloSaidaREL15
rotuloFalsoREL14: 	mov dword [ESP], 0
rotuloSaidaREL15: 	cmp dword[esp], 0

	je rotuloElse16
rotuloFimIf17: 	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	jmp rotuloFimIf17
null:
rotuloFimIf17: 	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	push 20
	pop eax
	mov dword[ebp - 0], eax
	push dword[ebp - 0]
	push 10
	pop eax
	cmp dword [ESP], eax
	jl rotuloFalsoREL18
	mov dword [ESP], 1
	jmp rotuloSaidaREL19
rotuloFalsoREL18: 	mov dword [ESP], 0
rotuloSaidaREL19: 	cmp dword[esp], 0

	je rotuloElse20
rotuloFimIf21: 	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	jmp rotuloFimIf21
null:
	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
rotuloFimIf21: 	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	push 1
	pop eax
	mov dword[ebp - 0], eax
	push dword[ebp - 0]
	push 10
	pop eax
	cmp dword [ESP], eax
	jl rotuloFalsoREL22
	mov dword [ESP], 1
	jmp rotuloSaidaREL23
rotuloFalsoREL22: 	mov dword [ESP], 0
rotuloSaidaREL23: 	cmp dword[esp], 0

	je rotuloElse24
rotuloFimIf25: 	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	jmp rotuloFimIf25
null:
	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
rotuloFimIf25: 	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	push 15
	pop eax
	mov dword[ebp - 0], eax
	push 5
	pop eax
	mov dword[ebp - 4], eax
	push dword[ebp - 0]
	push 10
	pop eax
	cmp dword [ESP], eax
	jl rotuloFalsoREL26
	mov dword [ESP], 1
	jmp rotuloSaidaREL27
rotuloFalsoREL26: 	mov dword [ESP], 0
rotuloSaidaREL27: 	push dword[ebp - 4]
	push 10
	pop eax
	cmp dword [ESP], eax
	jg rotuloFalsoREL28
	mov dword [ESP], 1
	jmp rotuloSaidaREL29
rotuloFalsoREL28: 	mov dword [ESP], 0
rotuloSaidaREL29: 	cmp dword [ESP + 4], 1
	jne rotuloFalsoMTL31
	pop eax
	cmp dword [ESP], eax
	jne rotuloFalsoMTL31
	mov dword [ESP], 1
	jmp rotuloSaidaMTL30
rotuloFalsoMTL31: 	mov dword [ESP], 0
rotuloSaidaMTL30: 	cmp dword[esp], 0

	je rotuloElse32
rotuloFimIf33: 	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	jmp rotuloFimIf33
null:
rotuloFimIf33: 	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	push 5
	pop eax
	mov dword[ebp - 0], eax
	push 5
	pop eax
	mov dword[ebp - 4], eax
	push dword[ebp - 0]
	push 10
	pop eax
	cmp dword [ESP], eax
	jl rotuloFalsoREL34
	mov dword [ESP], 1
	jmp rotuloSaidaREL35
rotuloFalsoREL34: 	mov dword [ESP], 0
rotuloSaidaREL35: 	push dword[ebp - 4]
	push 10
	pop eax
	cmp dword [ESP], eax
	jg rotuloFalsoREL36
	mov dword [ESP], 1
	jmp rotuloSaidaREL37
rotuloFalsoREL36: 	mov dword [ESP], 0
rotuloSaidaREL37: 	cmp dword [ESP + 4], 1
	jne rotuloFalsoMTL39
	pop eax
	cmp dword [ESP], eax
	jne rotuloFalsoMTL39
	mov dword [ESP], 1
	jmp rotuloSaidaMTL38
rotuloFalsoMTL39: 	mov dword [ESP], 0
rotuloSaidaMTL38: 	cmp dword[esp], 0

	je rotuloElse40
rotuloFimIf41: 	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	jmp rotuloFimIf41
null:
rotuloFimIf41: 	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	push 15
	pop eax
	mov dword[ebp - 0], eax
	push 15
	pop eax
	mov dword[ebp - 4], eax
	push dword[ebp - 0]
	push 10
	pop eax
	cmp dword [ESP], eax
	jl rotuloFalsoREL42
	mov dword [ESP], 1
	jmp rotuloSaidaREL43
rotuloFalsoREL42: 	mov dword [ESP], 0
rotuloSaidaREL43: 	push dword[ebp - 4]
	push 10
	pop eax
	cmp dword [ESP], eax
	jg rotuloFalsoREL44
	mov dword [ESP], 1
	jmp rotuloSaidaREL45
rotuloFalsoREL44: 	mov dword [ESP], 0
rotuloSaidaREL45: 	cmp dword [ESP + 4], 1
	jne rotuloFalsoMTL47
	pop eax
	cmp dword [ESP], eax
	jne rotuloFalsoMTL47
	mov dword [ESP], 1
	jmp rotuloSaidaMTL46
rotuloFalsoMTL47: 	mov dword [ESP], 0
rotuloSaidaMTL46: 	cmp dword[esp], 0

	je rotuloElse48
rotuloFimIf49: 	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	jmp rotuloFimIf49
null:
rotuloFimIf49: 	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	push 5
	pop eax
	mov dword[ebp - 0], eax
	push 15
	pop eax
	mov dword[ebp - 4], eax
	push dword[ebp - 0]
	push 10
	pop eax
	cmp dword [ESP], eax
	jl rotuloFalsoREL50
	mov dword [ESP], 1
	jmp rotuloSaidaREL51
rotuloFalsoREL50: 	mov dword [ESP], 0
rotuloSaidaREL51: 	push dword[ebp - 4]
	push 10
	pop eax
	cmp dword [ESP], eax
	jg rotuloFalsoREL52
	mov dword [ESP], 1
	jmp rotuloSaidaREL53
rotuloFalsoREL52: 	mov dword [ESP], 0
rotuloSaidaREL53: 	cmp dword [ESP + 4], 1
	jne rotuloFalsoMTL55
	pop eax
	cmp dword [ESP], eax
	jne rotuloFalsoMTL55
	mov dword [ESP], 1
	jmp rotuloSaidaMTL54
rotuloFalsoMTL55: 	mov dword [ESP], 0
rotuloSaidaMTL54: 	cmp dword[esp], 0

	je rotuloElse56
rotuloFimIf57: 	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	jmp rotuloFimIf57
null:
rotuloFimIf57: 	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	push 5
	pop eax
	mov dword[ebp - 0], eax
	push 15
	pop eax
	mov dword[ebp - 4], eax
	push dword[ebp - 0]
	push 10
	pop eax
	cmp dword [ESP], eax
	jl rotuloFalsoREL58
	mov dword [ESP], 1
	jmp rotuloSaidaREL59
rotuloFalsoREL58: 	mov dword [ESP], 0
rotuloSaidaREL59: 	push dword[ebp - 4]
	push 10
	pop eax
	cmp dword [ESP], eax
	jg rotuloFalsoREL60
	mov dword [ESP], 1
	jmp rotuloSaidaREL61
rotuloFalsoREL60: 	mov dword [ESP], 0
rotuloSaidaREL61: 	cmp dword [ESP + 4], 1
	je rotuloVerdadeMEL63
	cmp dword [ESP], 1
	je rotuloVerdadeMEL63
	mov dword [ESP + 4], 0
	jmp rotuloSaidaMEL62
rotuloVerdadeMEL63: 	mov dword [ESP + 4], 1
rotuloSaidaMEL62: 	add esp, 4
	cmp dword[esp], 0

	je rotuloElse64
rotuloFimIf65: 	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	jmp rotuloFimIf65
null:
rotuloFimIf65: 	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	push 5
	pop eax
	mov dword[ebp - 0], eax
	push 5
	pop eax
	mov dword[ebp - 4], eax
	push dword[ebp - 0]
	push 10
	pop eax
	cmp dword [ESP], eax
	jl rotuloFalsoREL66
	mov dword [ESP], 1
	jmp rotuloSaidaREL67
rotuloFalsoREL66: 	mov dword [ESP], 0
rotuloSaidaREL67: 	push dword[ebp - 4]
	push 10
	pop eax
	cmp dword [ESP], eax
	jg rotuloFalsoREL68
	mov dword [ESP], 1
	jmp rotuloSaidaREL69
rotuloFalsoREL68: 	mov dword [ESP], 0
rotuloSaidaREL69: 	cmp dword [ESP + 4], 1
	je rotuloVerdadeMEL71
	cmp dword [ESP], 1
	je rotuloVerdadeMEL71
	mov dword [ESP + 4], 0
	jmp rotuloSaidaMEL70
rotuloVerdadeMEL71: 	mov dword [ESP + 4], 1
rotuloSaidaMEL70: 	add esp, 4
	cmp dword[esp], 0

	je rotuloElse72
rotuloFimIf73: 	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	jmp rotuloFimIf73
null:
rotuloFimIf73: 	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	push 15
	pop eax
	mov dword[ebp - 0], eax
	push 15
	pop eax
	mov dword[ebp - 4], eax
	push dword[ebp - 0]
	push 10
	pop eax
	cmp dword [ESP], eax
	jl rotuloFalsoREL74
	mov dword [ESP], 1
	jmp rotuloSaidaREL75
rotuloFalsoREL74: 	mov dword [ESP], 0
rotuloSaidaREL75: 	push dword[ebp - 4]
	push 10
	pop eax
	cmp dword [ESP], eax
	jg rotuloFalsoREL76
	mov dword [ESP], 1
	jmp rotuloSaidaREL77
rotuloFalsoREL76: 	mov dword [ESP], 0
rotuloSaidaREL77: 	cmp dword [ESP + 4], 1
	je rotuloVerdadeMEL79
	cmp dword [ESP], 1
	je rotuloVerdadeMEL79
	mov dword [ESP + 4], 0
	jmp rotuloSaidaMEL78
rotuloVerdadeMEL79: 	mov dword [ESP + 4], 1
rotuloSaidaMEL78: 	add esp, 4
	cmp dword[esp], 0

	je rotuloElse80
rotuloFimIf81: 	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	jmp rotuloFimIf81
null:
rotuloFimIf81: 	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	push 15
	pop eax
	mov dword[ebp - 0], eax
	push 5
	pop eax
	mov dword[ebp - 4], eax
	push dword[ebp - 0]
	push 10
	pop eax
	cmp dword [ESP], eax
	jl rotuloFalsoREL82
	mov dword [ESP], 1
	jmp rotuloSaidaREL83
rotuloFalsoREL82: 	mov dword [ESP], 0
rotuloSaidaREL83: 	push dword[ebp - 4]
	push 10
	pop eax
	cmp dword [ESP], eax
	jg rotuloFalsoREL84
	mov dword [ESP], 1
	jmp rotuloSaidaREL85
rotuloFalsoREL84: 	mov dword [ESP], 0
rotuloSaidaREL85: 	cmp dword [ESP + 4], 1
	je rotuloVerdadeMEL87
	cmp dword [ESP], 1
	je rotuloVerdadeMEL87
	mov dword [ESP + 4], 0
	jmp rotuloSaidaMEL86
rotuloVerdadeMEL87: 	mov dword [ESP + 4], 1
rotuloSaidaMEL86: 	add esp, 4
	cmp dword[esp], 0

	je rotuloElse88
rotuloFimIf89: 	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	jmp rotuloFimIf89
null:
rotuloFimIf89: 	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	push 15
	pop eax
	mov dword[ebp - 0], eax
	push dword[ebp - 0]
	push 10
	pop eax
	cmp dword [ESP], eax
	jl rotuloFalsoREL90
	mov dword [ESP], 1
	jmp rotuloSaidaREL91
rotuloFalsoREL90: 	mov dword [ESP], 0
rotuloSaidaREL91: 	cmp dword [ESP], 1
	jne rotuloFalsoFL92
	mov dword [ESP], 0
	jmp rotuloSaidaFL93
rotuloFalsoFL92: 	mov dword [ESP], 1
rotuloSaidaFL93: 	cmp dword[esp], 0

	je rotuloElse94
rotuloFimIf95: 	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	jmp rotuloFimIf95
null:
rotuloFimIf95: 	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	push 5
	pop eax
	mov dword[ebp - 0], eax
	push dword[ebp - 0]
	push 10
	pop eax
	cmp dword [ESP], eax
	jl rotuloFalsoREL96
	mov dword [ESP], 1
	jmp rotuloSaidaREL97
rotuloFalsoREL96: 	mov dword [ESP], 0
rotuloSaidaREL97: 	cmp dword [ESP], 1
	jne rotuloFalsoFL98
	mov dword [ESP], 0
	jmp rotuloSaidaFL99
rotuloFalsoFL98: 	mov dword [ESP], 1
rotuloSaidaFL99: 	cmp dword[esp], 0

	je rotuloElse100
rotuloFimIf101: 	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	jmp rotuloFimIf101
null:
rotuloFimIf101: 	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	push 5
	pop eax
	mov dword[ebp - 0], eax
	push dword[ebp - 0]
	push @Integer
	call printf
	add esp, 8
	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	push 5
	push 5
	pop eax
	add dword[ESP], eax
	pop ecx
	push eax
	pop eax
	mov dword[ebp - 0], eax
	push dword[ebp - 0]
	push @Integer
	call printf
	add esp, 8
	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	push dword[ebp - 0]
	push 5
	pop eax
	add dword[ESP], eax
	pop ecx
	push eax
	pop eax
	mov dword[ebp - 0], eax
	push dword[ebp - 0]
	push @Integer
	call printf
	add esp, 8
	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	push 5
	push dword[ebp - 0]
	pop eax
	add dword[ESP], eax
	pop ecx
	push eax
	pop eax
	mov dword[ebp - 0], eax
	push dword[ebp - 0]
	push @Integer
	call printf
	add esp, 8
	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	push 10
	pop eax
	mov dword[ebp - 4], eax
	push 5
	pop eax
	mov dword[ebp - 0], eax
	push dword[ebp - 4]
	push dword[ebp - 0]
	pop eax
	add dword[ESP], eax
	pop ecx
	push eax
	pop eax
	mov dword[ebp - 0], eax
	push dword[ebp - 0]
	push @Integer
	call printf
	add esp, 8
	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	push 0
	pop eax
	mov dword[ebp - 4], eax
	push dword[ebp - 0]
	push dword[ebp - 0]
	pop eax
	add dword[ESP], eax
	pop ecx
	push eax
	pop eax
	mov dword[ebp - 4], eax
	push dword[ebp - 4]
	push @Integer
	call printf
	add esp, 8
	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	push 1
	pop eax
	mov dword[ebp - 8], eax
	push 1
	pop eax
	mov dword[ebp - 12], eax
	push 0
	pop eax
	mov dword[ebp - 16], eax
	push 0
	pop eax
	mov dword[ebp - 20], eax
	mov edx, ebp
	lea eax, [edx - 16]
	push eax
	push @Integer
	call scanf
	add esp, 8
	push dword[ebp - 8]
	push @Integer
	call printf
	add esp, 8
	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	push dword[ebp - 12]
	push @Integer
	call printf
	add esp, 8
	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	push 2
	pop eax
	mov dword[ebp - 24], eax
rotuloWhile102: 	push dword[ebp - 24]
	push dword[ebp - 16]
	pop eax
	cmp dword [ESP], eax
	jg rotuloFalsoREL104
	mov dword [ESP], 1
	jmp rotuloSaidaREL105
rotuloFalsoREL104: 	mov dword [ESP], 0
rotuloSaidaREL105: 	cmp dword[esp], 0

	je rotuloFimWhile103
	push dword[ebp - 8]
	push dword[ebp - 12]
	pop eax
	add dword[ESP], eax
	pop ecx
	push eax
	pop eax
	mov dword[ebp - 20], eax
	push dword[ebp - 20]
	push @Integer
	call printf
	add esp, 8
	pushrotuloStringLN: db '' , 10,0
	call printf
	add esp, 4
	push dword[ebp - 12]
	pop eax
	mov dword[ebp - 8], eax
	push dword[ebp - 20]
	pop eax
	mov dword[ebp - 12], eax
	push dword[ebp - 24]
	push 1
	pop eax
	add dword[ESP], eax
	pop ecx
	push eax
	pop eax
	mov dword[ebp - 24], eax
	jmp rotuloWhile102
rotuloFimWhile103: 	leave
	ret

section .data

rotuloStringLN: db '' , 10,0
@Integer: db '%d',0
@IntegerLN: db '%d',10,0
