; multi-segment executable file template.

data segment
    ; add your data here!
    pkey db "press any key...$"  
    
    CR equ 13
    LF equ 10
     
    mens db 'Escriba la cadena que sera escrita al reves', CR,LF,'$'
    salto db CR,LF,'$'
     
    cadena label byte
     
    cant db 11
    max  db 00
    campo db  11 dup(' ')
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
    ; 2. Ingresar por teclado una 
    ; cadena de maximo 10 caracteres y luego imprimirla invertida.
  
   inicio:
    mov ax,@data
    mov ds,ax
    push ds
    pop es
    
    mov ah,09h
    lea dx,mens
    int 21h
    
    mov ah,0ah
    lea dx,cadena
    int 21h
     
     	mov ah,09h
    lea dx,salto
    int 21h
     
    mov cl,cant
    mov bx, offset campo
    add bl, cant
     
   loopCadena:
    mov dl,[bx]
    mov ah,02h
    int 21h
    dec bl
    dec cl
    cmp cl,0
    je salir
    jmp loopCadena
     
   salir:
    mov dl,[bx]
    mov ah,02h
    int 21h
    mov ax,4c00h
    int 21h
     
    end  inicio
       
    
    ; ---------------------------
            
    ;lea dx, pkey
    lea dx, mens
    mov ah, 9
    int 21h        ; output string at ds:dx
    
    ; wait for any key....    
    mov ah, 1
    int 21h
    
    mov ax, 4c00h ; exit to operating system.
    int 21h    
ends

end start ; set entry point and stop the assembler.
