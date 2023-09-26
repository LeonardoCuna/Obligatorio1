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
    
   







    
    
    public void MostrarTablero(){
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
        
        
        
        

        
        for (int i = 0; i < tablero.length; i++) {
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
    
    
    
    public String primerMatriz(Scanner input){
     // String [][] datos= 
        String linea = input.nextLine();
        return linea;
        
    }
    public String[][] leerMatriz(Scanner input, String linea){
           
        String[] posString = linea.split(" ");

        int filas= Integer.parseInt(posString[0]);
        int columnas= Integer.parseInt(posString[1]);
        
          String [][] matriz = new String [filas][columnas];
        
          for(int i=0; i<=filas; i++){

                  String coordStrings = input.nextLine();
                  String[] coord = coordStrings.split(" ");
                  
                  for(int j=0; j<coord.length; j++){
                      if(coord[j]=="|"){
                          matriz[i][j]="|";
                      }
                      if(coord[j]=="\\"){
                          matriz[i][j]="\\";
                      }
                      if(coord[j]=="/"){
                          matriz[i][j]="/";
                      }
                      if(coord[j]=="-"){
                          matriz[i][j]="-";
                      }
                  }
              }
          
          
          return matriz;
    }
     
    
}
