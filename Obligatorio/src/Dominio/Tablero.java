/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Lcuna y lucas nohacenada gonzalez
 */
public class Tablero implements Cloneable{
    private int filas;
    private int columnas;
    private int nivel;
    private String[][] tablero;
    
    
    String rojo="\033[31m";
    String azul="\033[34m"; 
    String reset="\u001B[0m";
    
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
    
    public String[][] getTablero() {
        return tablero;
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
    
    
   
    public static String[][] generarMatrizRandom(int n, int m) {
        String[][] matriz = new String[n][m];
        Random rand = new Random();
        int color = rand.nextInt(2);
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int randomIndex = rand.nextInt(4);
                 String[] caracteresRojos = {"-R", "|R", "/R", "\\R"};
                 String[] caracteresAzules = {"-A", "|A", "/A", "\\A"};
               
                if(color==1){
                     matriz[i][j] = caracteresRojos[randomIndex];
                }else{
                    matriz[i][j] = caracteresAzules[randomIndex];
                }
                
               
            }
        }

        return matriz;
    }
    
     public String[] desordenarMatriz(int nivel,int filas,int columnas){
         Random rand = new Random();
         String[]movimientos = new String[nivel];
         
         boolean repetido = false;
         for(int i=0; i<nivel; i++){
             int filaRandom = rand.nextInt(filas)+1;
             int columnaRandom = rand.nextInt(columnas)+1;
 
             
             for(int j=0; j<nivel; j++){
                 if(movimientos[j]!=null && movimientos[j].equals(filaRandom+","+columnaRandom)){
                     repetido=true;
                 }
             }
             if(!repetido){
                this.aplicarJugada(filaRandom, columnaRandom);
             }else{
                 i--;
             }

             
           
             movimientos[i]=filaRandom+","+columnaRandom;
         }

         return movimientos;
     }
    
     public void armarTableroRandom(String[][] matriz){
        
        String[][] tablero=this.armarPlantilla();

         
        int fil=0;
        for(int i=2; i<tablero.length; i+=2){
            int col=0;
            
            for(int j=4; j<tablero[i].length; j+=4){
                String color;
                if(matriz[fil][col].charAt(1)=='R'){
                   color=rojo;
                }else{
                   color=azul;
                }
                
                if(matriz[fil][col].charAt(0)=='|'){
                    tablero[i][j]=color+"|"+reset;
                }else{
                    if(matriz[fil][col].charAt(0)=='-'){
                       tablero[i][j]=color+"-"+reset; 
                    }else{
                       if(matriz[fil][col].charAt(0)=='/'){
                          tablero[i][j]=color+"/"+reset; 
                       }else{
                          tablero[i][j]=color+"\\"+reset; 
                       }
                    }
                }
                
                col++;
                    
            }
            fil++;
        }
        
       
       
       this.tablero=tablero;
    }
    

  
    public int[] primerMatriz(Scanner input){
        String linea = input.nextLine();
        
        String[] posString = linea.split(" ");
        int[] dimensiones=new int[2];
        dimensiones[0]=Integer.parseInt(posString[0]);
        dimensiones[1]=Integer.parseInt(posString[1]);
        //System.out.println(input.nextLine());
        
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
                String color;
                if(datos[fil][col].charAt(1)=='R'){
                   color=rojo;
                }else{
                   color=azul;
                }
                
                if(datos[fil][col].charAt(0)=='|'){
                    tablero[i][j]=color+"|"+reset;
                }else{
                    if(datos[fil][col].charAt(0)=='-'){
                       tablero[i][j]=color+"-"+reset; 
                    }else{
                       if(datos[fil][col].charAt(0)=='/'){
                          tablero[i][j]=color+"/"+reset; 
                       }else{
                          tablero[i][j]=color+"\\"+reset; 
                       }
                    }
                }
                
                col++;
                    
            }
            fil++;
        }
        
       this.nivel=Integer.parseInt(input.nextLine());
       
       this.tablero=tablero;
    }
    
    
    
    
   public void aplicarJugada(int fila, int columna){
        
        if((this.tablero[fila*2][columna*4]).contains("-")){
            this.cambiarFila(fila,columna);
        }
        
        if((this.tablero[fila*2][columna*4]).contains("|")){
            this.cambiarColumna(fila,columna);
        }
        
        if((this.tablero[fila*2][columna*4]).contains("/")){
            this.cambiarDiagonal(fila,columna);
        }
       if((this.tablero[fila*2][columna*4]).contains("\\")){
            cambiarContraDiagonal(fila,columna);
       }
    }
    
  
      public void cambiarColumna(int fila, int columna){
        
        for(int i=2; i<this.tablero.length; i+=2){
            String comparar=this.tablero[i][columna*4];
               if(comparar.contains(rojo)){
                   this.tablero[i][columna*4]=comparar.replace(rojo, azul);
               }
               if(comparar.contains(azul)){
                   this.tablero[i][columna*4]=comparar.replace(azul, rojo);
               }
        }
    }
      
     public void cambiarDiagonal(int fila, int columna){
          int filaTab=fila*2;
          int colTab=columna*4;
          
          //para arriba
          for(int i=filaTab; i<this.tablero.length && i>=0 && colTab<this.tablero[0].length; i-=2){
             
                 String comparar=this.tablero[i][colTab];
              if(comparar.contains(rojo)){
                   this.tablero[i][colTab]=comparar.replace(rojo, azul);
               }
               if(comparar.contains(azul)){
                   this.tablero[i][colTab]=comparar.replace(azul, rojo);
               }
               
               colTab+=4;
             
          }
          
          filaTab=fila*2;
          colTab=columna*4;
        //para abajo
          for(int i=filaTab+2; i<this.tablero.length && colTab>=4; i+=2){
                colTab-=4;
                 String comparar=this.tablero[i][colTab];
              if(comparar.contains(rojo)){
                   this.tablero[i][colTab]=comparar.replace(rojo, azul);
               }
               if(comparar.contains(azul)){
                   this.tablero[i][colTab]=comparar.replace(azul, rojo);
               }
               
               
             
          }
        
          
    }
     
     
     public void cambiarContraDiagonal(int fila, int columna){
          int filaTab=fila*2;
          int colTab=columna*4;
          
          //para arriba
          for(int i=filaTab; i<this.tablero.length && i>=0 && colTab>=4; i-=2){
             
                 String comparar=this.tablero[i][colTab];
              if(comparar.contains(rojo)){
                   this.tablero[i][colTab]=comparar.replace(rojo, azul);
               }
               if(comparar.contains(azul)){
                   this.tablero[i][colTab]=comparar.replace(azul, rojo);
               }
               
               colTab-=4;
             
          }
          
          filaTab=fila*2;
          colTab=columna*4;
        //para abajo
          for(int i=filaTab+2; i<this.tablero.length && i>=0 && colTab<this.tablero[0].length-4; i+=2){
                colTab+=4; 
                 String comparar=this.tablero[i][colTab];
              if(comparar.contains(rojo)){
                   this.tablero[i][colTab]=comparar.replace(rojo, azul);
               }
               if(comparar.contains(azul)){
                   this.tablero[i][colTab]=comparar.replace(azul, rojo);
               } 
               
               
             
          }
        
        
          
    }
     
    
    
    public void cambiarFila(int fila, int columna){
    for(int j=4; j<this.tablero[0].length; j+=4){
        String comparar = this.tablero[fila*2][j];
        if(comparar.contains(rojo)){
            this.tablero[fila*2][j] = comparar.replace(rojo, azul);
        }
        if(comparar.contains(azul)){
            this.tablero[fila*2][j] = comparar.replace(azul, rojo);
        }
    }
}
    
            
    public void mostrarTablero(){
        for(int i = 0; i < this.tablero.length; i++) {
            for (int j = 0; j < this.tablero[i].length; j++) {
                if(this.tablero[i][j]==null){

                    System.out.print(" ");
                }else{

                    System.out.print(this.tablero[i][j]);
                }
                
            }
            System.out.println(); // Saltar a una nueva línea después de cada fila
        } 
    }
    


        public boolean sonIguales(Tablero otro) {
        String[][] tablero1 = this.getTablero();
        String[][] tablero2 = otro.getTablero();

        if (tablero1.length != tablero2.length || tablero1[0].length != tablero2[0].length) {
            return false;
        }

        for (int i = 2; i < tablero1.length; i+=2) {
            for (int j = 4; j < tablero1[0].length; j+=4) {
                
                if(tablero1[i][j].contains(rojo) && (tablero2[i][j].contains(azul))){
                    return false;
                }
                if(tablero1[i][j].contains(azul) && (tablero2[i][j].contains(rojo))){
                    return false;
                }
                
                if (!tablero1[i][j].equals(tablero2[i][j])) {
                    return false;
                }
            
            }
        }
        return true;
    }
        
        
        public boolean Finalizado(){
            boolean esRojo=true;
            boolean esAzul=true;
            boolean finalizado=true;
            
            //es azul
            for (int i = 0; i < this.filas * 2 + 2; i++) {
                for (int j = 0; j < this.columnas * 4 + 3; j++) {
                    if(this.tablero[i][j].contains(rojo)){
                        esAzul=false;
                    }
                }
            }
            
            
            //es rojo
            for (int i = 0; i < this.filas * 2 + 2; i++) {
                for (int j = 0; j < this.columnas * 4 + 3; j++) {
                    if(this.tablero[i][j].contains(azul)){
                        esRojo=false;
                    }
                }
            }
            
            if(esRojo && esAzul){
                finalizado=false;
            }
            
            return finalizado;
        }
        
        
        @Override
   public Tablero clone() {
    Tablero nuevoTablero = new Tablero();
    nuevoTablero.filas = this.filas;
    nuevoTablero.columnas = this.columnas;
    nuevoTablero.nivel = this.nivel;

   nuevoTablero.tablero = new String[(this.filas * 2) + 2][(this.columnas * 4) + 3];
    for (int i = 0; i < this.filas * 2 + 2; i++) {
        for (int j = 0; j < this.columnas * 4 + 3; j++) {
            nuevoTablero.tablero[i][j] = this.tablero[i][j];
        }
    }

    return nuevoTablero;
}
    
    }
