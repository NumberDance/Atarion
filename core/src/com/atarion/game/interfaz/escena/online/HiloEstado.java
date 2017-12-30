package com.atarion.game.interfaz.escena.online;


import com.badlogic.gdx.Gdx;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class HiloEstado implements Runnable 
{
    private Socket cliente = null;
    private boolean servidor = true;
    
    
    public HiloEstado(Socket cliente, boolean servidor)
    { 
        this.cliente = cliente;
        this.servidor = servidor;
    }
    
    
    @Override
    public void run()
    { 
        if(servidor)
        {
            for(;;)
            {
                try
                {    
                    String message = new BufferedReader(new InputStreamReader(cliente.getInputStream())).readLine();
                    System.out.println("Got client message: " + message);
                
                    cliente.getOutputStream().write("PONG\n".getBytes());
                } 
                catch (IOException ex)
                {} 
            }
        }
        else
        {
            for(;;)
            {
                try
                {
                    cliente.getOutputStream().write("PING\n".getBytes());
                    
                    String response = new BufferedReader(new InputStreamReader(cliente.getInputStream())).readLine();
                    Gdx.app.log("INFO","El server responde: " + response);
                } 
                catch (IOException ex)
                {}
            }
        }
    }
}
