
package Interfaz;

import Dominio.Partida;
import Dominio.Tablero;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class vistaMetodos {
    
    public static void mostrarJugadas(Tablero x, Partida Juego) {
        System.out.println("");
        System.out.println("Estas fueron tus jugadas:");
        System.out.println("");
        int contadorJugada = 1;
        for (Tablero jugada : Juego.getJugadas()) {
            System.out.println("Jugada "+contadorJugada+":");
                    jugada.mostrarTablero();
                    contadorJugada++;
                }
    }
       
    public static int validarEntero(String mensaje) {
    Scanner tec = new Scanner(System.in);
    int numero = 0;
    boolean entradaValida = false;

    while (!entradaValida) {
        try {
            System.out.println(mensaje);
            String entrada = tec.next();

            if (entrada.equalsIgnoreCase("x") || entrada.equalsIgnoreCase("X")) {
                return -3; 
            }
            if (entrada.equalsIgnoreCase("h") || entrada.equalsIgnoreCase("H")) {
                return -2; 
            }
            
            if (Integer.parseInt(entrada) > 9) {
                System.out.println("Error: El juego no permite numero mayor a 9 unidades");
                continue; 
            }

            numero = Integer.parseInt(entrada);
            entradaValida = true;

        } catch (NumberFormatException e) {
            System.out.println("Error: Debes ingresar un número entero.");
        }
    } 
    return numero;
}
    public static int[] ingresarDimensionesTablero() {
        Scanner tec = new Scanner(System.in);
        
        System.out.println("");
        int nivel = validarEntero("Ingresar nivel del tablero");
    
        int filas =  validarEntero("Ingresar filas del tablero");
        
        int columnas =  validarEntero("Ingresar columnas del tablero");
        
        int[] dimensiones = {nivel, filas, columnas};
        return dimensiones;
    }
    
     public static void ganaste(Tablero x,int movimientos,Partida Juego){
            System.out.println("");
                System.out.println("Felicidades, ganaste, este es el tablero final");
                System.out.println("");
                x.mostrarTablero();
                System.out.println("Lo lograste en: "+ movimientos + " Movimientos");
                System.out.println("");
               
        }
     
     public static void deseaJugar(){
        Scanner tec= new Scanner(System.in); 
        System.out.println("¿Deseas jugar de nuevo? S/N");
        String decision = tec.nextLine();

        
        switch(decision.toUpperCase()){
            case "S": inicio();
                break;
                
            case "N": System.out.println("Gracias por jugar");
            inicio();
            break;
            
            default: System.out.println("Usted ingreso una opción incorrecta");
                System.out.println("");
            
        }

    }
     
     public static void mostrarMovimientosArrayList(Partida Juego){

         
         if(Juego.getMovimientos().size()==0){
             System.out.println("Aun no tienes movimientos realizados");
         }else{
                      System.out.println("-----------------");
                      System.out.println("Movimientos realizados:");
                      System.out.println("-----------------");
             
                        for (String movimiento : Juego.getMovimientos()) {
                    System.out.println(movimiento +" ");
                } 
         }
     }
 
     public static int[] movimiento(Partida Juego){
    
    Scanner tec= new Scanner(System.in);         
    System.out.println("--Jugada--");

        int fila=validarEntero("Ingrese fila");
    
         if(fila==-3){
        System.out.println("Juego terminado.");
        inicio();         
          }
    
         if(fila==-2){
        mostrarMovimientosArrayList(Juego);    
       
          }
    
         int columna=validarEntero("Ingrese columna");
    
         if(columna==-3){
        System.out.println("Juego terminado.");
        inicio();           
         }
    
          if(columna==-2){
        mostrarMovimientosArrayList(Juego);    
        movimiento(Juego);
              }
    
        int[] retorno = new int[2];

          int x = fila;
          int y = columna;
    
        Juego.addMovimiento(x+","+y);
    
          retorno[0] = x;
          retorno[1] = y;

    return retorno; 
}
     
     public static String solicitarDatosInicio(){
                Scanner tec= new Scanner(System.in);
       
            System.out.println("Bienvenido a Soliflips");
        System.out.println("");
        System.out.println("a) Tomar datos del archivo 'datos.txt' ");
        System.out.println("b) Usar el tablero predefinido");
        System.out.println("""
                           c) Usar un tablero al azar. En este caso se ingresa m (cantidad de filas), n (cantidad de columnas), nivel (1 a 8). El
                           tablero a generar debe ser resoluble en esa cantidad de pasos indicada por el nivel.""");
        System.out.println("");       
        System.out.println("Elige tu opción");
        String decision = tec.next();
        return decision;
        }
     
     public static void inicio(){   
        String decision=solicitarDatosInicio();
        
        switch(decision){
            case "a":
                Scanner input = null;
            try { 
                input = new Scanner(new File(".\\Test\\datos.txt"));
                
            } catch (FileNotFoundException ex) {
                System.out.println("No se encontro el archivo");
            }
            Tablero aux = new Tablero();
            int[] datos= aux.primerMatriz(input);

            Tablero tabArchivo=new Tablero(3, datos[0], datos[1]);
            tabArchivo.armarTableroArchivo(input);
            
            
             System.out.println("-------------------------");
             System.out.println("Tablero a resolver:");
            tabArchivo.mostrarTablero();
      
            Partida Juego2 = new Partida();  
             
            while(!tabArchivo.resuelto()){
                

               int[] movimientoRealizar= movimiento(Juego2);
               
             
               if(movimientoRealizar[0]==-1 && -1==movimientoRealizar[1]){
                
                if(Juego2.getJugadas().size()>=2){
                    System.out.println("Tablero anterior:");
                    tabArchivo.setTablero(Juego2.volverJugada(tabArchivo));
                    tabArchivo.mostrarTablero();
                }else{
                    System.out.println("No se puede volver más movimientos");
                }
                
                }else{
                    if(tabArchivo.movimientoValido(movimientoRealizar[0], movimientoRealizar[1])){
                          tabArchivo.aplicarJugada(movimientoRealizar[0], movimientoRealizar[1]);
                          Juego2.sumarMovimiento();
                        System.out.println("");

                        System.out.println("Realizaste el movimiento: "+movimientoRealizar[0]+","+movimientoRealizar[1]);
                        System.out.println("Tablero actual:");

                       tabArchivo.mostrarTablero();
                       Tablero jugada=tabArchivo.clone();
                       Juego2.addJugadas(jugada);
                      }else{
                        System.out.println("ATENCIÓN: NO SE REALIZA MOVIMIENTO A CAUSA DE COORDENADAS NO VÁLIDAS O ELECCION DE HISTORIAL ");
                    }


                }
            }
            
            if(tabArchivo.resuelto()){
                ganaste(tabArchivo,Juego2.getCantMovimientos(),Juego2);
                long tiempoTranscurrido = Principal.obtenerTiempoTranscurrido();
                System.out.println("Tiempo transcurrido: " + tiempoTranscurrido + " segundos");
                 deseaJugar();
            
            }
            
            
          
                break;
                
              
                
            case "b":
                input = null;
            try { 
                input = new Scanner(new File(".\\Test\\predefinido.txt"));
                
            } catch (FileNotFoundException ex) {
                System.out.println("No se encontro el archivo");
            }
            Tablero predefinido = new Tablero();
            int[] pre= predefinido.primerMatriz(input);

            Tablero tableroPredefinido=new Tablero(3, pre[0], pre[1]);
            tableroPredefinido.armarTableroArchivo(input);
            
            Tablero predefinidoColor = tableroPredefinido.clone();
            predefinidoColor.aplicarJugada(4, 4);
            predefinidoColor.aplicarJugada(5, 6);
            predefinidoColor.aplicarJugada(5, 4);            
            
                System.out.println("-------------------------");
                System.out.println("Tablero a resolver:");
                tableroPredefinido.mostrarTablero();
                
             Partida Juego3 = new Partida();  
             
            while(!tableroPredefinido.sonIguales(predefinidoColor)){
                System.out.println("Tablero anterior:");

               int[] movimientoRealizar= movimiento(Juego3);
               
             
               if(movimientoRealizar[0]==-1 && -1==movimientoRealizar[1]){
                
                  if(Juego3.getJugadas().size()>=2){
                    System.out.println("Tablero anterior:");
                    tableroPredefinido.setTablero(Juego3.volverJugada(tableroPredefinido));
                    tableroPredefinido.mostrarTablero();
                    
                  }else{
                    System.out.println("No se puede volver más movimientos");
                  } 
                   
                   
                }else{
                if(tableroPredefinido.movimientoValido(movimientoRealizar[0], movimientoRealizar[1])){
                      tableroPredefinido.aplicarJugada(movimientoRealizar[0], movimientoRealizar[1]);
                      Juego3.sumarMovimiento();
                    System.out.println("");

                    System.out.println("Realizaste el movimiento: "+movimientoRealizar[0]+","+movimientoRealizar[1]);
                    System.out.println("Tablero actual:");

                   tableroPredefinido.mostrarTablero();
                   Tablero jugada=tableroPredefinido.clone();
                   Juego3.addJugadas(jugada);
                  }else{
                    System.out.println("ATENCIÓN: NO SE REALIZA MOVIMIENTO A CAUSA DE COORDENADAS NO VÁLIDAS O ELECCION DE HISTORIAL ");
                }


                }
              }
            
            if(tableroPredefinido.sonIguales(predefinidoColor)){
                ganaste(tableroPredefinido,Juego3.getCantMovimientos(),Juego3);
                long tiempoTranscurrido = Principal.obtenerTiempoTranscurrido();
                System.out.println("Tiempo transcurrido: " + tiempoTranscurrido + " segundos");
                 deseaJugar();
            
            }
            
                break;
                
                
            case "c":
               int[] dimensiones=ingresarDimensionesTablero();
               int nivel=dimensiones[0];
               int filas = dimensiones[1];
               int columnas = dimensiones[2];
            
            Partida Juego = new Partida();
            Tablero azarFinalizado= new Tablero(nivel,filas,columnas); 
            String[][] matriz = azarFinalizado.generarMatrizRandom(filas,columnas);
            azarFinalizado.armarTableroRandom(matriz);
            azarFinalizado.mostrarTablero();
            //arma un tablero de un solo color random
            
            
            Tablero aOrdenar = azarFinalizado.clone();
            
            
            
            String[] movimientos =aOrdenar.desordenarMatriz(nivel,filas,columnas);
         
            
            
            
             for(int i=0; i<movimientos.length; i++){
                 System.out.println(movimientos[i]);
             }
 
                System.out.println("");
                System.out.println("Tu tablero de nivel: "+nivel + " con " +filas+ " filas y "+columnas+ " columnas es:");
                aOrdenar.mostrarTablero();
                Tablero primera=aOrdenar.clone();
                Juego.addJugadas(primera);
            while(!aOrdenar.sonIguales(azarFinalizado)){
                
               int[] movimientoRealizar= movimiento(Juego);
               
               
               if(movimientoRealizar[0]==-1 && -1==movimientoRealizar[1]){
                if(Juego.getJugadas().size()>=2){
                    System.out.println("Tablero anterior:");
                    aOrdenar.setTablero(Juego.volverJugada(aOrdenar));
                    aOrdenar.mostrarTablero();
                }else{
                    System.out.println("No se puede volver más movimientos");
                }
            }else{
             if(aOrdenar.movimientoValido(movimientoRealizar[0], movimientoRealizar[1])){
                  aOrdenar.aplicarJugada(movimientoRealizar[0], movimientoRealizar[1]);
                  Juego.sumarMovimiento();
                System.out.println("");
                
                System.out.println("Realizaste el movimiento: "+movimientoRealizar[0]+","+movimientoRealizar[1]);
                System.out.println("Tablero actual:");
                
               aOrdenar.mostrarTablero();
               Tablero jugada=aOrdenar.clone();
               Juego.addJugadas(jugada);
              }else{
                System.out.println("ATENCIÓN: NO SE REALIZA MOVIMIENTO A CAUSA DE COORDENADAS NO VÁLIDAS O ELECCION DE HISTORIAL ");
            }
            
            }
            }
            if(aOrdenar.sonIguales(azarFinalizado)){      
                ganaste(aOrdenar,Juego.getCantMovimientos(),Juego);
                long tiempoTranscurrido = Principal.obtenerTiempoTranscurrido();
                System.out.println("Tiempo transcurrido: " + tiempoTranscurrido + " segundos");
                 deseaJugar();

            }
            
                break;
                
                

                
            default: 
            System.out.println("Ingresaste una opción incorrecta, recuerda que debe ser a, b o c.");
            inicio();
                    break;
                  
        }       
    }
     
}
