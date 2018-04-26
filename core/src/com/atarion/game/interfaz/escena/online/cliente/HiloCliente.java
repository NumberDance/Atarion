package com.atarion.game.interfaz.escena.online.cliente;


import com.atarion.game.entidad.jugador.Jugador;
import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;


public class HiloCliente extends Thread
{
    private Socket cliente;
    private BufferedReader lector;
    private Jugador jugador, jugador2;
    private EscenaCliente escena;
    private Json conversor = new Json(); 
    
    
    public HiloCliente(EscenaCliente escena)
    {
        this.cliente = escena.getCliente();
        this.lector = escena.getLector();
            
        this.jugador = escena.getHumano();
        this.jugador2 = escena.getHumano2();
        this.escena = escena;
            
        this.conversor.setTypeName(null);
        this.conversor.setUsePrototypes(false);
        this.conversor.setIgnoreUnknownFields(true);
        this.conversor.setOutputType(JsonWriter.OutputType.json);
    }
    
    
    @Override
    public void run()
    {
        try
        {
            MensajeJSON estados = new MensajeJSON();
            
            estados.concatenar(jugador.enviarEstado(),"estados");
            if(jugador2.isInteraccion())
            { 
                estados.concatenar(jugador2.enviarEstado(),"estados"); 
                jugador.setInteraccion(false);
            }
            Gdx.app.log("INFO: El cliente envia: ",estados.getMensaje());
            
            estados.enviar(cliente.getOutputStream());
            escena.actualizarPartida(this.lector.readLine());
        } 
        catch (IOException ex)
        {}
    }
}
