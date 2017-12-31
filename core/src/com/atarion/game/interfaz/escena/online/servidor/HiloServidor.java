package com.atarion.game.interfaz.escena.online.servidor;


import com.atarion.game.entidad.jugador.Jugador;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashSet;


public class HiloServidor implements Runnable
{
    private Socket cliente = null;
    private HashSet<Jugador> estados = new HashSet<Jugador>();
    
    
    public HiloServidor(Socket cliente)
    { this.cliente = cliente; }
    
    
    @Override
    public void run()
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
}
