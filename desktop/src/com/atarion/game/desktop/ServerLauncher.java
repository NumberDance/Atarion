package com.atarion.game.desktop;

import com.atarion.game.interfaz.escena.online.servidor.EscenaServidor;

public class ServerLauncher  
{   
    public static void main(String [] args)
    {   
        System.out.println("SERVER MODE");
        new EscenaServidor().run();
    }
}
