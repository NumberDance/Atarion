package com.atarion.game.interfaz.escena.online.servidor;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


public final class EscenaServidor implements Runnable
{
    private HashMap<Integer,Socket> clientes = new HashMap<Integer,Socket>();
    private Integer cuenta = 0, capacidad = 2;

    
    @Override
    public void run()
    {
        try
        {
            ServerSocket servidor = new ServerSocket(20595);
            
            while(cuenta < capacidad)
            {
                System.out.println("Esperando clientes...");
                cuenta++;
                
                clientes.put(cuenta,servidor.accept());
                System.out.println("Cliente conectado: " + clientes.get(cuenta).getInetAddress());
            }
            
            clientes.entrySet().forEach(cliente -> { new HiloServidor(cliente.getValue(),cliente.getKey(),clientes).run(); } );
        } 
        catch (IOException ex)
        {}
    }
}
