package com.atarion.game.interfaz.escena.online;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import java.util.HashMap;


public final class ServidorEscenas 
{
    private ServerSocket servidor;
    private HashMap<Integer,Socket> clientes = new HashMap<Integer,Socket>();
    private Integer cuenta = 0, capacidad = 1;
    
    
    public ServidorEscenas()
    {
        servidor = Gdx.net.newServerSocket(Protocol.TCP,20595,new ServerSocketHints());
            
        while(cuenta < capacidad)
        {
            System.out.println("Esperando clientes...");
            cuenta++;
                
            clientes.put(cuenta,servidor.accept(null));
            System.out.println("Cliente conectado: " + clientes.get(cuenta).getRemoteAddress());
        }
            
        this.iniciarPartida();
    }
    private void iniciarPartida()
    { clientes.entrySet().forEach(cliente -> { new HiloEstado(cliente.getValue()).run(); }); }
}
