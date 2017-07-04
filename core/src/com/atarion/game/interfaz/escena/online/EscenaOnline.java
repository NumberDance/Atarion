package com.atarion.game.interfaz.escena.online;

import com.atarion.game.interfaz.escena.Escena;
import com.badlogic.gdx.audio.Music;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public abstract class EscenaOnline extends Escena
{
    private ServerSocket servidor = null;
    private HashSet<Socket> clientes = new HashSet<Socket>();
    
    
    protected EscenaOnline(Music tema)
    { super(tema); }
    protected EscenaOnline(ServerSocket servidor)
    { 
        super(null);
        this.servidor = servidor; 
    }
    
    
    public void arrancarPartidaCliente()
    {   
        try
        {
            Socket cliente = servidor.accept();
            clientes.add(cliente);
        
            BufferedReader salida = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            
            while(salida.ready())
            { System.out.println(salida.readLine()); }
        } 
        catch (IOException ex) 
        {}
    }
}
