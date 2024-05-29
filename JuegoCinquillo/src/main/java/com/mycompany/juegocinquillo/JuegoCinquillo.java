package com.mycompany.juegocinquillo;

import java.awt.Color;
import java.util.Scanner;

/**
 *
 * @author Efrain
 */
public class JuegoCinquillo {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        Canvas cvs;
        cvs = new Canvas("Quintillo game", 900, 700, Color.red);
        cvs.setVisible(true);
        Juego quintillo = new Juego();
        int n;
        do{
            quintillo.jugar();
            System.out.println("¿Quieres volver a jugar?");
            System.out.println("1) Si \n 2)No");
            n = scn.nextInt();
        }while(n != 2);
        System.out.println("Gracias por jugar :)");
    }}
