package com.atarion.game.interfaz.escena.online;


import com.atarion.game.entidad.jugador.humano.wheel.Traveler;
import com.atarion.game.interfaz.escena.Escena;
import com.badlogic.gdx.audio.Music;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


public class EscenaCliente extends Escena
{
    private Socket cliente;
    private PrintWriter salida; 
    
    
    public EscenaCliente(Music tema)
    { 
        super(tema);
        
        try
        {
            cliente = new Socket("192.168.1.100",20595);
        } 
        catch (IOException ex)
        {}
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
        new HiloEstado(cliente,false,humano).run();
    }
}
