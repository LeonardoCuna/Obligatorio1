
package Interfaz;


import static Interfaz.vistaMetodos.*;
/**
 *
 * @author lucas y leo
 */
public class Principal {
    private static long tiempoInicio;
    
    public static long obtenerTiempoTranscurrido() {
        return (System.currentTimeMillis()- tiempoInicio) / 1000; 
    }
    
    
    public static void main(String[] args) {
        tiempoInicio = System.currentTimeMillis();
        inicio();
    }
    
}
