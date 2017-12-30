package com.atarion.game.interfaz.escena.online;

import com.atarion.game.entidad.jugador.humano.wheel.Traveler;
import com.atarion.game.interfaz.escena.Escena;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class EscenaCliente extends Escena
{
    private Socket cliente;
    private PrintWriter salida; 
    
    
    public EscenaCliente(Music tema)
    { 
        super(tema);
        cliente = Gdx.net.newClientSocket(Protocol.TCP,"192.168.1.100",20595,new SocketHints());
    }

    
    @Override
    public void show()
    {
        super.show();
        humano = new Traveler(genesis,true);
    }
    @Override
    public void render(float delta)
    {
        super.render(delta);
        
        try
        { 
            cliente.getOutputStream().write("PING\n".getBytes()); 
            
            String response = new BufferedReader(new InputStreamReader(cliente.getInputStream())).readLine();
            Gdx.app.log("INFO","El server responde: " + response);
        }
        catch (IOException ex)
        {}
    }
}
