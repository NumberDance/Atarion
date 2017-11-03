package com.atarion.game.interfaz.escena.online;

import com.atarion.game.interfaz.escena.Escena;
import com.badlogic.gdx.audio.Music;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public abstract class EscenaOnline extends Escena
{
    private ServerSocket servidor = null;
    private Socket cliente = null;
    private HashSet<Socket> clientes = null;
    
    
    protected EscenaOnline(Music tema)
    { super(tema); }
    
    
    public void montarServidor(ServerSocket servidor)
    {   
        this.servidor = servidor;
        this.clientes = new HashSet<Socket>();
        
        try
        {
            System.out.println("Esperando clientes");
            
            Socket cliente = this.servidor.accept();
            clientes.add(cliente);
            
            System.out.println("Cliente conectado: " + cliente.getInetAddress());
        } 
        catch (IOException ex) 
        {}
    }
    public void montarCliente(Socket cliente)
    {
        this.cliente = cliente;
    }
}
