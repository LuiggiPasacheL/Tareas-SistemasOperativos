; multi-segment executable file template.

data segment
    ; add your data here!
    CAD db "Cooooolllllocar_caddddena","$"
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
    
    LEA si, CAD
    
E2: mov al, [si]
    mov bl, [si+1]
    cmp al, bl
    jne E1
    mov al, 07 ; timbre en ascii
    mov [si],al      
    jmp E2
            
E1: inc si        
    mov al, [si]
    cmp al, "$"
    je FIN
    jmp E2
FIN:           
           
    lea dx,CAD 
    mov ah, 9
    int 21h        ; output string at ds:dx
    
    ; wait for any key....    
    mov ah, 1
    int 21h
    
    mov ax, 4c00h ; exit to operating system.
    int 21h    
ends

end start ; set entry point and stop the assembler.
