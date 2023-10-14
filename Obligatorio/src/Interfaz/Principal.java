/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Interfaz;

import Dominio.Tablero;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 *
 * @author lucas
 */
public class Principal {
  


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
       
            tabArchivo.cambiarDiagonal(2, 2);
            
            tabArchivo.mostrarTablero();

            /* String rojo="\033[31m";
            String azul="\033[34m"; 
            
            String linearoja=rojo+"-";
            if(linearoja.equals(rojo+"-")){
                System.out.println("si");
            }
            */
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
            //hasta aca tablero queda hecho en un solo color
            
            
            //Tablero desordenado = azarFinalizado.clone();
              
            String[] movimientos =azarFinalizado.desordenarMatriz(nivel,filas,columnas);
            
            // desordenado.mostrarTablero();
             for(int i=0; i<movimientos.length; i++){
                 System.out.println(movimientos[i]);
             }
                break;
                
            default: System.out.println("Ingresaste una opción incorrecta, recuerda que debe ser a, b o c.");
            solicitarDatosInicio();
                    break;
                  
        }       
    }
    
    
    public static void main(String[] args) {
        inicio();
    }
    
}
