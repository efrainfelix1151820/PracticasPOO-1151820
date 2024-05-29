package com.mycompany.juegocinquillo;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Efrain
 */
public class Juego {
    private ArrayList<Jugador> jugadores;
    private Baraja barajaJuego;
    private Carta cartaTablero;
    private int numJugadores;
    private String tablero;
        public Juego()
        {
            this.numJugadores = 0;
            jugadores = new ArrayList();
            barajaJuego = new Baraja();
            cartaTablero = new Carta();
            tablero = "";
        }
        
        public void jugar()
        {
            Scanner s = new Scanner(System.in);
            boolean jugadorSinCartas = false;
            int n;
            int turno = 0;
            asignarNumeroJugadores();
            repartirCartas();
            
            System.out.println("Inicio juego el quintillo. \n");
            do{
                System.out.println("Carta en la mesa: \t Cartas de baraja restante: "+barajaJuego.getNumeroCartasEnMesa());
                System.out.println(tablero+"\n");
            
                System.out.println("Cartas de "+jugadores.get(turno).getNombre());
                jugadores.get(turno).verCartasEnMano();
            
                System.out.println("Que numero de carta quiere insertar en el tablero: ");
                n = s.nextInt();
            
                if(tablero.isEmpty())
                {
                    cartaTablero = jugadores.get(turno).removerCarta(n-1);
                    tablero = cartaTablero.toString();
                }
                else if(cartaTablero.getPalo().equals(jugadores.get(turno).getCartaEnMano(n-1).getPalo()) || cartaTablero.getValor() == jugadores.get(turno).getCartaEnMano(n-1).getValor())
                {
                    cartaTablero = jugadores.get(turno).removerCarta(n-1);
                    tablero = cartaTablero.toString();
                    System.out.println("Tu carta fue colocada. \n");
                }
                else
                {
                    if(barajaJuego.getNumeroCartasEnMesa() > 0)
                    {
                        System.out.println("No se puede colocar en el tablero. Toma una");
                        jugadores.get(turno).recibirCarta(barajaJuego.darCarta());
                        System.out.println(jugadores.get(turno).getCartaEnMano(jugadores.get(turno).getNumeroCartas()-1));
                    
                        if(cartaTablero.getPalo().equals(jugadores.get(turno).getCartaEnMano(jugadores.get(turno).getNumeroCartas()-1).getPalo()) 
                        || cartaTablero.getValor() == jugadores.get(turno).getCartaEnMano(jugadores.get(turno).getNumeroCartas()-1).getValor())
                        {
                            cartaTablero = jugadores.get(turno).removerCarta(jugadores.get(turno).getNumeroCartas()-1);
                            tablero = cartaTablero.toString();
                            System.out.println("Tu carta fue colocada. \n");
                        }
                        else
                        {
                            System.out.println("La carta que se agarró, tampoco se puede colocar. \n");
                        }
                    }
                    else
                    {
                        System.out.println("Se agotaron las cartas de la baraja, se pasa turno. \n");
                    }
                }
                
                if(jugadores.get(turno).getNumeroCartas() == 0)
                {
                    System.out.println("El ganador es: "+jugadores.get(turno).getNombre());
                    System.out.println("FELICIDADES.");
                    jugadorSinCartas = true;
                }
                else
                {
                    System.out.println("Siguiente jugador. \n");
                }
                
                if(turno == (jugadores.size()-1))
                {
                    turno = 0;
                }
                else
                {
                    turno++;
                }
                
            }while(jugadorSinCartas!=true);
            
        }
        
        public void asignarNumeroJugadores()
        {
            int n;
            String nomAux;
            Scanner scn = new Scanner(System.in);
            Scanner scnCadenas = new Scanner(System.in);
            
            System.out.println("Ingrese el numero de jugadores:(2-4)");
            n = scn.nextInt();
            while(n < 2 || n > 4)
            {
                System.out.println("Número de jugadores invalido, ingrese un numero valido (2-4): ");
                n = scn.nextInt();
            }
            
            for(int i = 0; i < n; i++)
            {
                jugadores.add(new Jugador());
                System.out.println("Ingrese el nombre del jugador "+(i+1)+": ");
                nomAux = scnCadenas.nextLine();
                jugadores.get(i).setNombre(nomAux);
            }
            
            System.out.println("Jugadores creados. :) \n");
        }
        
        public void repartirCartas()
        {
            barajaJuego.crearBaraja();
            barajaJuego.revolverBaraja();
            for(int i = 0; i < jugadores.size(); i++)
            {
                for(int j = 0; j < 4; j++)
                {
                    jugadores.get(i).recibirCarta(barajaJuego.darCarta());
                }
            }
        }
    
}
