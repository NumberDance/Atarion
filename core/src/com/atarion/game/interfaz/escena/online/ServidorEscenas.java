package com.atarion.game.interfaz.escena.online;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


public final class ServidorEscenas 
{
    private ServerSocket servidor;
    private HashMap<Integer,Socket> clientes = new HashMap<Integer,Socket>();
    private Integer cuenta = 0, capacidad = 1;
    
    
    public ServidorEscenas()
    {
        try
        {
            servidor = new ServerSocket(20595);
            
            while(cuenta < capacidad)
            {
                System.out.println("Esperando clientes...");
                cuenta++;
                
                clientes.put(cuenta,servidor.accept());
                System.out.println("Cliente conectado: " + clientes.get(cuenta).getInetAddress());
            }
            
            this.iniciarPartida();
        } 
        catch (IOException ex)
        {}
    }
    private void iniciarPartida()
    { clientes.entrySet().forEach(cliente -> { new HiloEstado(cliente.getValue()).run(); }); }
}
