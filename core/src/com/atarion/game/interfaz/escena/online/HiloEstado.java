package com.atarion.game.interfaz.escena.online;


import com.atarion.game.entidad.Entidad;
import com.atarion.game.entidad.jugador.Jugador;
import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class HiloEstado implements Runnable 
{
    private Socket cliente = null;
    private boolean servidor = true;
    private Jugador estado = null;
    private Gson conversor = new Gson();
    
    
    public HiloEstado(Socket cliente,boolean servidor,Jugador estado)
    { 
        this.cliente = cliente;
        this.servidor = servidor;
        this.estado = estado;
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
            try
            {
                cliente.getOutputStream().write(estado.getDireccion().toString().getBytes());
                    
                String response = new BufferedReader(new InputStreamReader(cliente.getInputStream())).readLine();
                Gdx.app.log("INFO","El server responde: " + response);
            } 
            catch (IOException ex)
            {}
        }
    }
}
