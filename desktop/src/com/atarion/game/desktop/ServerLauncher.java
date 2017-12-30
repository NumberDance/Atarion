package com.atarion.game.desktop;

import com.atarion.game.interfaz.escena.online.ServidorEscenas;

public class ServerLauncher  
{   
    public static void main(String [] args)
    {   
        System.out.println("SERVER MODE");
        ServidorEscenas servidor = new ServidorEscenas();
    }
}
