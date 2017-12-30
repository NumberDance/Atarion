package com.atarion.game.interfaz.escena.online;

import com.badlogic.gdx.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class HiloEstado implements Runnable 
{
    private Socket cliente;
    
    
    public HiloEstado(Socket cliente)
    { this.cliente = cliente; }
    
    
    @Override
    public void run()
    { 
        for(;;)
        {
            
            try
            {    
                BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                System.out.println(entrada.readLine());
                
                System.out.println(entrada.readLine() + "\n");
                entrada.close();
            } 
            catch (IOException ex)
            {} 
        }
    }
}
