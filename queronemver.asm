global main
extern printf
extern scanf
section .text
main: 	   ;Entrada do Programa
	   push ebp
	   mov ebp, esp
	leave
	ret
