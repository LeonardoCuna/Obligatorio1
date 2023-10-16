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

    
    public Partida(){
        this.cantMovimientos=0;
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
}
