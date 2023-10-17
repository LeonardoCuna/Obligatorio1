/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

import java.util.ArrayList;

/**
 *
 * @author Lcuna
 */
public class Partida {
    private ArrayList<Tablero> jugadas = new ArrayList();
    private int tiempo;
    private int cantMovimientos;
    private ArrayList<String> movimientos = new ArrayList();

    
    public Partida(){
        this.cantMovimientos=0;
    }
    
    
    public ArrayList<String> getMovimientos() {
        return movimientos;
    }
    
    public void addMovimiento(String x) {
       movimientos.add(x);
    }
    
    public ArrayList<Tablero> getJugadas() {
        return jugadas;
    }

    public void addJugadas(Tablero unTab) {
       jugadas.add(unTab);
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public int getCantMovimientos() {
        return cantMovimientos;
    }

    public void setCantMovimientos(int cantMovimientos) {
        this.cantMovimientos = cantMovimientos;
    }
    
    
    public void sumarMovimiento(){
        this.cantMovimientos+=1;
    }
    
    
    
    public String[][] volverJugada(Tablero actual){
        
       
            // Accede al penúltimo elemento
            Tablero unTab = this.jugadas.get(jugadas.size() - 2);
            String[][] anterior= unTab.getTablero();
            this.jugadas.remove(this.jugadas.size() - 1);
            return anterior;
            
        
         
    
    }
}
