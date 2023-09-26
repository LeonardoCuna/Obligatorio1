/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Interfaz;

import Dominio.Tablero;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucas
 */
public class Principal {

    
    public static void inicio(){
        Scanner tec= new Scanner(System.in);
        
        
        
        System.out.println("Bienvenido a Soliflips");
        System.out.println("");
        System.out.println("a) Tomar datos del archivo 'datos.txt' ");
        System.out.println("b) Usar el tablero predefinido");
        System.out.println("""
                           c) Usar un tablero al azar. En este caso se ingresa m (cantidad de filas), n (cantidad de columnas), nivel (1 a 8). El
                           tablero a generar debe ser resoluble en esa cantidad de pasos indicada por el nivel.""");
        System.out.println("");       
        System.out.println("Elige tu opci�n");
                
        String decision = tec.next();
        
        if(decision.equals("a")){
            try { 
                Scanner input = new Scanner(new File(".\\Test\\datos.txt"));
                Tablero tabArchivo = new Tablero();
       String linea = tabArchivo.primerMatriz(input);
       String [][] matriz = tabArchivo.leerMatriz(input, linea);
       for(int i=0; i<=matriz.length; i++){
           for(int j=0;j<=matriz[0].length; j++){
               System.out.println(matriz[i][j]);
           }
           
       }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }

       
       
       
               
        }
       // System.out.println(System.getProperty("user.dir"));
           
        if(decision.equals("b")){
            
            Tablero predef= new Tablero();
            predef.MostrarTablero();
            
            Tablero otro= new Tablero(4, 3, 5);
            otro.MostrarTablero();
            
            Tablero otrotab= new Tablero(4, 8, 10);
            otrotab.MostrarTablero();
         }
        if(decision.equals("c")){
        
         }
    }
    
    
    public static void main(String[] args) {
        inicio();
    }
    
}
