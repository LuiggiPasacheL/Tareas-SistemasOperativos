; multi-segment executable file template.

data segment
    ; add your data here!
    pkey db "press any key...$"    
    cad db "SISTEMAAAAAAS", "$" ; hay 6 "car"
    car db "A"      
    cuenta db 0
ends

stack segment
    dw   128  dup(0)
ends

code segment
start:
; set segment registers:
    mov ax, data
    mov ds, ax
    mov es, ax

    ; add your code here          
    ; 1.programa para contar el numero de 
    ;ocurrencias de car en la cadena car
    
    lea si, cad
    
    mov cl, 0
   e2:
    mov al, [si]
    cmp al, car
    jne e1
    inc cl
   e1:
    inc si
    mov al, [si]
    cmp al, "$"
    je fin
    jmp e2: 
   fin:
    mov [cuenta], al 
    
    ;-----------------------------    
    lea dx, pkey
    mov ah, 9
    int 21h        ; output string at ds:dx
    
    ; wait for any key....    
    mov ah, 1
    int 21h
    
    mov ax, 4c00h ; exit to operating system.
    int 21h    
ends

end start ; set entry point and stop the assembler.
