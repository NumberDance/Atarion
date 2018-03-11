package com.atarion.game.interfaz.escena.online.servidor;


import com.atarion.game.interfaz.escena.online.MensajeJSON;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.Semaphore;


public final class EscenaServidor extends Thread
{
    private HashMap<String,SimpleEntry<Socket,String>> clientes = new HashMap<>();
    private Integer cuenta = 0, capacidad = 2;
    private final Semaphore semaforo = new Semaphore(1,true);
    private boolean listo = false;
    private HashSet<MensajeJSON> iniciales = new HashSet<>();

    
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

    
    public void agregarEstadoInicial(MensajeJSON estado)
    { this.iniciales.add(estado); }
        
    
    public HashMap<String, SimpleEntry<Socket, String>> getClientes()
    { return clientes; }
    public Semaphore getSemaforo()
    { return semaforo; }
    public HashSet<MensajeJSON> getIniciales()
    { return iniciales; }
}
