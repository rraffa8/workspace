#include <hidef.h> /* for EnableInterrupts macro */
#include "derivative.h" /* include peripheral declarations */


void main(void) {
  EnableInterrupts;
  /* include your code here */
  SCIC1 = 0	;     /*8 bits, sin paridad*/ 
  SCIC2 = 0X8;	/*receiver on con polling */
  SCIC3 = 0;	/*no detiene en caso de errores*/
  SCIBDH = 0;	/**/
  SCIBDL = 0XD;	/*cargamos el resultado de la division para los 9600b/s*/
  PTADD_PTADD0 = 0;  /* colocamos el puerto PTAD0 en input */
  PTAPE_PTAPE0 = 1 ;	/*Colocamos el puerto de pull up encendido para el puerto de PTA0*/
 
  while (PTAD_PTAD0 == 1){}  /*esperamos que se precione el boton*/
  SCID = 0x68;				/* Cargamos el codigo asccii de la h */
  while (SCIS1_TDRE == 0){} /* validamos que termine la transmision de la h*/
  SCID = 0x6F;				/* Cargamos el codigo asccii de la o */
  while (SCIS1_TDRE == 0){} 
    SCID = 0x6C;

  for(;;) {
    __RESET_WATCHDOG();	/* feeds the dog */
  } /* loop forever */
  /* please make sure that you never leave main */
}
