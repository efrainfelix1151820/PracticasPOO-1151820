package com.mycompany.juegocinquillo;

import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author Efrain
 */
public class Baraja {
    private ArrayList<Carta> barajaEspanola;
    private String[] tipoPalo = {"Oros","Copas","Espadas","Bastos"};
    private int[] valoresCarta = {1, 2, 3, 4, 5, 6, 7, 10, 11, 12};
        public Baraja()
        {
            barajaEspanola = new ArrayList();
        }
        public void crearBaraja()
        {
            for(int i = 0; i < tipoPalo.length; i++)
            {
                for(int j = 0; j < valoresCarta.length; j++)
                {
                    barajaEspanola.add(new Carta(valoresCarta[j], tipoPalo[i], true) );
                }
            }
        }
        
        public void verBaraja()
        {
            System.out.println(barajaEspanola.toString());
        }
        
        public void revolverBaraja()
        {
            Collections.shuffle(barajaEspanola);
        }
        
        public void voltearBaraja()
        {
            for(int i = 0; i < barajaEspanola.size(); i++)
            {
                barajaEspanola.get(i).voltearCarta();
            }
        }
        
        public Carta darCarta()
        {
            return barajaEspanola.remove(0);
        }
        
        public int getNumeroCartasEnMesa()
        {
            return barajaEspanola.size();
        }
}
