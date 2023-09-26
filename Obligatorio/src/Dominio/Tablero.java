/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

import java.util.Scanner;

/**
 *
 * @author Lcuna y lucas nohacenada gonzalez
 */
public class Tablero {
    private int filas;
    private int columnas;
    private int nivel;
    
    
    public Tablero(){
        this.filas=5;
        this.columnas=6;
        this.nivel=3;
    }
    
    public Tablero(int nivel, int filas, int columnas){
        this.nivel=nivel;
        this.filas=filas;
        this.columnas=columnas;
    }
    
    

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    public int altoTablero(){
        int cantidadAlto=(this.getFilas()*2)+2;
        return cantidadAlto;       
    }
    
    public int largoTablero(){
        int cantidadLargo=(this.getColumnas()*4)+3;
        return cantidadLargo;
    }
    
   







    
    
    public String[][] armarPlantilla(){
        String[][] tablero=new String[this.altoTablero()][this.largoTablero()];
        tablero[0][0]=" ";
        
        int filas=this.getFilas();
        int columnas=this.getColumnas();
        
        // PARA NUMEROS DE COLUMNAS
        int auxCol=1;
        for(int j=0; j<tablero[0].length; j++){
            if(j%4==0 && j!=0 && auxCol<=columnas){
                tablero[0][j]=Integer.toString(auxCol);
                auxCol++;
            }
        }
        
        // PARA NUMEROS DE FILAS
        int auxFila=1;
        for(int i=0; i<tablero.length; i++){
            if(i%2==0 && i!=0 && auxFila<=filas){
                tablero[i][0]=Integer.toString(auxFila);
                auxFila++;
            }
        }
        
        
         for(int i=1; i<tablero.length; i+=2){
            for(int j=2; j<tablero[i].length; j+=4){
                
                tablero[i][j]="+";
            }
        }
         
         for(int i=0; i<tablero.length; i+=2){
            for(int j=2; j<tablero[i].length; j+=4){
                if(i!=0){
                    tablero[i][j]="|";

                }
            }
        }
         
         for(int i=1; i<tablero.length; i+=2){
            for(int j=2; j<tablero[i].length; j++){
                
                if(tablero[i][j]==null){
                    tablero[i][j]="-";
                }
            }
        }
        
        
        
        

        
       /* for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if(tablero[i][j]==null){
                    //System.out.printf("%1s ", "");
                    System.out.print(" ");
                }else{
                    //System.out.printf("%1s ", tablero[i][j]);
                    System.out.print(tablero[i][j]);
                }
                
            }
            System.out.println(); // Saltar a una nueva línea después de cada fila
        }
    */
        return tablero;
        
    }
    
    
    
    public int[] primerMatriz(Scanner input){
        String linea = input.nextLine();
        
        String[] posString = linea.split(" ");
        int[] dimensiones=new int[2];
        dimensiones[0]=Integer.parseInt(posString[0]);
        dimensiones[1]=Integer.parseInt(posString[1]);
        return dimensiones;
    }
    
    
    public String[][] leerMatriz(Scanner input){
          
        int filas= this.getFilas();
        int columnas=this.getColumnas();
        
        String [][] matriz = new String [filas][columnas];
        
        
        for(int i=0; i<matriz.length; i++){
            String[] linea=input.nextLine().split(" ");
            for(int j=0; j<linea.length; j++){
                matriz[i][j]=linea[j];
            }
        }
     
          return matriz;
    }
     
    
    public void armarTableroArchivo(Scanner input){
        
        String[][] tablero=this.armarPlantilla();
        
        String[][] datos= this.leerMatriz(input);
        int fil=0;
        for(int i=2; i<tablero.length; i+=2){
            int col=0;
            
            for(int j=4; j<tablero[i].length; j+=4){
                
                if(datos[fil][col].equals("|R")){
                   tablero[i][j]="|";
                   
                }
                if(datos[fil][col].equals("|A")){
                   tablero[i][j]="|";
                   
                }
                if(datos[fil][col].equals("-R")){
                   tablero[i][j]="-";
                   
                }
                if(datos[fil][col].equals("-A")){
                   tablero[i][j]="-";
                   
                }
                if(datos[fil][col].equals("/R")){
                   tablero[i][j]="/";
                   
                }
                if(datos[fil][col].equals("/A")){
                   tablero[i][j]="/";
                   
                }
                if(datos[fil][col].equals("\\R")){
                   tablero[i][j]="\\";
                   
                }
                if(datos[fil][col].equals("\\A")){
                   tablero[i][j]="\\";
                   
                }
                
                col++;
                    
            }
            fil++;
        }
        
        for(int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if(tablero[i][j]==null){
                    //System.out.printf("%1s ", "");
                    System.out.print(" ");
                }else{
                    //System.out.printf("%1s ", tablero[i][j]);
                    System.out.print(tablero[i][j]);
                }
                
            }
            System.out.println(); // Saltar a una nueva línea después de cada fila
        }
    }
    
}
