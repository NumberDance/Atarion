package com.atarion.game.desktop;

import com.atarion.game.interfaz.escena.online.EscenaPVP;
import java.io.IOException;
import java.net.ServerSocket;

public class ServerLauncher  
{   
    public static void main(String [] args)
    {   
        System.out.println("SERVER MODE");
        
        try
        { new EscenaPVP().montarServidor(new ServerSocket(20595)); } 
        catch (IOException ex)
        {}
    }
}
