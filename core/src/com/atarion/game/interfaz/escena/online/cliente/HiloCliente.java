package com.atarion.game.interfaz.escena.online.cliente;

import com.atarion.game.entidad.jugador.Jugador;
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
    private Jugador jugador;
    private EscenaCliente escena;
    private Json conversor = new Json(); 
    
    
    public HiloCliente(EscenaCliente escena)
    {
        this.cliente = escena.getCliente();
        this.lector = escena.getLector();
            
        this.jugador = escena.getHumano();
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
            jugador.enviarEstado().enviar(cliente.getOutputStream());
            
            String response = this.lector.readLine();
            Gdx.app.log("INFO","El server responde: " + response);
            
            escena.updateGlobalStates(response);
        } 
        catch (IOException ex)
        {}
    }
}
