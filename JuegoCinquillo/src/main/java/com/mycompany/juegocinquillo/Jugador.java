package com.mycompany.juegocinquillo;

import java.util.ArrayList;

/**
 *
 * @author Efrain
 */
public class Jugador {
    private ArrayList<Carta> mazoEnMano;
    private String nombre;
        public Jugador()
        {
            this.nombre = "";
            mazoEnMano = new ArrayList();
        }
        
        public void setNombre(String n)
        {
            this.nombre = n;
        }
        
        public String getNombre()
        {
            return nombre;
        }
        
        public void recibirCarta(Carta aux)
        {
            mazoEnMano.add(aux);
        }
        
        public void verCartasEnMano()
        {
            System.out.println(mazoEnMano.toString()+"\n");
        }
        
        
        public Carta removerCarta(int indice)
        {
            return mazoEnMano.remove(indice);
        }

        public Carta getCartaEnMano(int indice){
            return mazoEnMano.get(indice);
        }
        
        public int getNumeroCartas()
        {
            return mazoEnMano.size();
        }
}
