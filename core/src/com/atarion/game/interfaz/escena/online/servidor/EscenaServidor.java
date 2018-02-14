package com.atarion.game.interfaz.escena.online.servidor;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;


public final class EscenaServidor extends Thread
{
    private HashMap<String,SimpleEntry<Socket,String>> clientes = new HashMap<String,SimpleEntry<Socket,String>>();
    private Integer cuenta = 0, capacidad = 2;
    private final Semaphore semaforo = new Semaphore(1,true);
    private boolean listo = false;

    
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
                
                clientes.put("Jugador|" + cuenta,new SimpleEntry<>(servidor.accept(),""));
                System.out.println("Cliente conectado: " + clientes.get("Jugador|" + cuenta).getKey().getInetAddress());
            }
            clientes.entrySet().forEach(cliente -> { new HiloServidor(cliente,this).start(); });
        } 
        catch (IOException ex)
        {}
    }

    
    public HashMap<String, SimpleEntry<Socket, String>> getClientes()
    { return clientes; }
    public Semaphore getSemaforo()
    { return semaforo; }
}
