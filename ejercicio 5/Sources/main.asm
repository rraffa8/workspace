;*******************************************************************
;* This stationery serves as the framework for a user application. *
;* For a more comprehensive program that demonstrates the more     *
;* advanced functionality of this processor, please see the        *
;* demonstration applications, located in the examples             *
;* subdirectory of the "Freescale CodeWarrior for HC08" program    *
;* directory.                                                      *
;*******************************************************************

; Include derivative-specific definitions
            INCLUDE 'derivative.inc'
            
;
; export symbols
;
            XDEF _Startup
            ABSENTRY _Startup

;
; variable/data section
;
            ORG    RAMStart         ; Insert your data definition here
ExampleVar: DS.B   1

;
; code section
;
            ORG    ROMStart
            
;Programa en c para velocidad de motor dc, conectado al canal 0 del timer 1 (TPM1CH0) 
;en base a los estados recibidos en los pines PTA0 y PTA1 de acuerdo a la tabla, 
;utilice PWM a una frecuencia de 800hz, asuma la frecuencia del bus de 2.5MHZ.
_Startup:
            LDHX   #RAMEnd+1        ; initialize the stack pointer
            TXS
            CLI			; enable interrupts
			BCLR 0, PTADD
			BCLR 1, PTADD
			LDA PTAPE
			ORA #$3
			STA PTAPE
			MOV #$9, TPM1SC ;bus clock rate, prescale divisor 2
			MOV #$28, TPM1C1SC ; no interrupciones, edge aligned PWM , high true pulse
			
			LDHX #1563 ; HABILITAMOS FLANCO DE SUBIDA
			
			STHX TPM1MOD ;
			
			LDHX #313	;
			STHX TPM1C0V ;
			
mainLoop:
            ; Insert your code here
            NOP

            feed_watchdog
            BRA    mainLoop
			
;**************************************************************
;* spurious - Spurious Interrupt Service Routine.             *
;*             (unwanted interrupt)                           *
;**************************************************************

spurious:				; placed here so that security value
			NOP			; does not change all the time.
			RTI

;**************************************************************
;*                 Interrupt Vectors                          *
;**************************************************************

            ORG	$FFFA

			DC.W  spurious			;
			DC.W  spurious			; SWI
			DC.W  _Startup			; Reset
