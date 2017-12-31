package com.atarion.game.interfaz.escena.online.cliente;


import com.atarion.game.entidad.jugador.humano.Humano;
import com.atarion.game.entidad.jugador.humano.wheel.Traveler;
import com.atarion.game.interfaz.escena.Escena;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Json;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;


public class EscenaCliente extends Escena
{
    private Socket cliente;
    private PrintWriter salida; 
    private HashSet<Humano> enemigos = new HashSet<Humano>();
    private HashSet<Humano> aliados = new HashSet<Humano>();
    private Json conversor = new Json();
    
    
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
        new HiloCliente(cliente,humano,this).run();
    }
    
    
    public void updateGlobalStates(String states)
    {
        /*if(this.humano2 == null)
        { humano2 = new Traveler(genesis,false); }*/
        
        humano2 = this.conversor.fromJson(Traveler.class,states);
        //humano2.recibirEstado(states);
    }
}
