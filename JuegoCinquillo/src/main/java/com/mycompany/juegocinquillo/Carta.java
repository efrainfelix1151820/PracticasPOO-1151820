package com.mycompany.juegocinquillo;

/**
 *
 * @author Efrain
 */
public class Carta {
    private int valor;
    private String palo;
    private boolean isVisible;
        public Carta()
        {
            this.palo = "";
            this.valor = 0;
            this.isVisible = true;
        }
        
        public Carta(int val, String p, boolean v)
        {
            this.valor = val;
            this.palo = p;
            this.isVisible = v;
        }
        
        public int getValor()
        {
            return valor;
        }
        
        public String getPalo()
        {
            return palo;
        }
        
        public void setValor(int v)
        {
            this.valor = v;
        }
        
        public void setPalo(String p)
        {
            this.palo = p;
        }
        
        public void voltearCarta()
        {
            if(isVisible == true)
            {
                isVisible = false;
            }
            else
            {
                isVisible = true;
            }
        }
        
        @Override
        public String toString()
        {
            if(isVisible == true)
            {
                return "[ "+valor+" / "+palo+" ]";
            }
            else
            {
                return "[ Espanola ]";
            }
        }
}
