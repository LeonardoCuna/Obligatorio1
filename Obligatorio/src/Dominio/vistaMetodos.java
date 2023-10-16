
package Dominio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class vistaMetodos {
    
       
    public static int validarEntero(String mensaje) {
        Scanner tec = new Scanner(System.in);
        int numero = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                System.out.println(mensaje);
                numero = tec.nextInt();
                entradaValida = true; //entrada es válida.
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número entero.");
                tec.next(); // Limpir el buffer
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
    
     public static void ganaste(Tablero x,int movimientos){
            System.out.println("");
                System.out.println("Felicidades, ganaste, este es el tablero final");
                System.out.println("");
                x.mostrarTablero();
                System.out.println("Lo lograste en: "+ movimientos + " Movimientos");
                deseaJugar();
        }
     
     public static void deseaJugar(){
        Scanner tec= new Scanner(System.in); 
        System.out.println("¿Deseas jugar de nuevo? S/N");
        String decision = tec.nextLine();
        
        switch(decision.toUpperCase()){
            case "S": inicio();
                break;
                
            case "N": System.out.println("Gracias por jugar");
            break;
            
            default: System.out.println("Usted ingreso una opción incorrecta");
                System.out.println("");
                break;
        }
    }
 
     public static int[] movimiento(){
        
        //falta movimiento
                 Scanner tec= new Scanner(System.in);         
                 System.out.println("--Jugada--");

                 System.out.println("Ingrese movimiento");
                 String movimiento = tec.nextLine();
                 
                 if(movimiento=="X"){
                     
                 }
          
                  String[] coordenadas = movimiento.split(" ");
                  int x = Integer.parseInt(coordenadas[0]);
                  int y = Integer.parseInt(coordenadas[1]);
                 
                 int[] retorno = new int[2];
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
            tabArchivo.mostrarTablero();
      

            
          
                break;
                
              
                
            case "b":
                // System.out.println(System.getProperty("user.dir")); 
            Tablero predef= new Tablero();
            predef.armarPlantilla();
            
            Tablero otro= new Tablero(4, 3, 5);
            otro.armarPlantilla();
            
            Tablero otrotab= new Tablero(4, 8, 10);
            otrotab.armarPlantilla();
            
            
            
            
            
                break;
                
                
            case "c":
               int[] dimensiones=ingresarDimensionesTablero();
               int nivel=dimensiones[0];
               int filas = dimensiones[1];
               int columnas = dimensiones[2];
            
            Tablero azarFinalizado= new Tablero(nivel,filas,columnas); 
             String[][] matriz = azarFinalizado.generarMatrizRandom(filas,columnas);
            azarFinalizado.armarTableroRandom(matriz);
            azarFinalizado.mostrarTablero();
            //arma un tablero de un solo color random
            
            
            Tablero aOrdenar = azarFinalizado.clone();
            

              String[] movimientos =aOrdenar.desordenarMatriz(nivel,filas,columnas);
         
            
            
            int movimientosNecesitadosPorUser = 0;
            
             for(int i=0; i<movimientos.length; i++){
                 System.out.println(movimientos[i]);
             }
 
                System.out.println("");
                System.out.println("Tu tablero de "+nivel+ "nivel" + " con " +filas+ " filas y "+columnas+ "columnas es:");
                aOrdenar.mostrarTablero();
             
            while(!aOrdenar.sonIguales(azarFinalizado)){
                
               int[] movimientoRealizar= movimiento();
               
             aOrdenar.aplicarJugada(movimientoRealizar[0], movimientoRealizar[1]);  
             movimientosNecesitadosPorUser++;
                System.out.println("");
                System.out.println("Realizaste el movimiento: "+movimientoRealizar[0]+","+movimientoRealizar[1]);
                System.out.println("Tablero actual:");
                
               aOrdenar.mostrarTablero();
            }
            
            
            
            if(aOrdenar.sonIguales(azarFinalizado)){
                ganaste(aOrdenar,movimientosNecesitadosPorUser);
               
            }

                break;
                
                

                
            default: 
            System.out.println("Ingresaste una opción incorrecta, recuerda que debe ser a, b o c.");
            inicio();
                    break;
                  
        }       
    }
     
}
