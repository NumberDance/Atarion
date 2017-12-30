package com.atarion.game.desktop;

import com.atarion.game.interfaz.escena.online.ServidorEscenas;
import java.io.IOException;
import java.net.ServerSocket;

public class ServerLauncher  
{   
    public static void main(String [] args)
    {   
        System.out.println("SERVER MODE");
        new ServidorEscenas();
    }
}
