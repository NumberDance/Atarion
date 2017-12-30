package com.atarion.game.interfaz.escena.online;

import com.badlogic.gdx.Gdx;
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
		String message = new BufferedReader(new InputStreamReader(cliente.getInputStream())).readLine();
		Gdx.app.log("PingPongSocketExample", "got client message: " + message);
                
                cliente.getOutputStream().write("PONG\n".getBytes());
            } 
            catch (IOException ex)
            {} 
        }
    }
}
