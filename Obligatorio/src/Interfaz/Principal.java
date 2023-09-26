/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Interfaz;

import Dominio.Tablero;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


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
        System.out.println("Elige tu opción");
                
        String decision = tec.next();
        
        if(decision.equals("a")){
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
       
       
       
               
        }
       // System.out.println(System.getProperty("user.dir"));
           
        if(decision.equals("b")){
            
            Tablero predef= new Tablero();
            predef.armarPlantilla();
            
            Tablero otro= new Tablero(4, 3, 5);
            otro.armarPlantilla();
            
            Tablero otrotab= new Tablero(4, 8, 10);
            otrotab.armarPlantilla();
         }
        if(decision.equals("c")){
        
         }
    }
    
    
    public static void main(String[] args) {
        inicio();
    }
    
}
