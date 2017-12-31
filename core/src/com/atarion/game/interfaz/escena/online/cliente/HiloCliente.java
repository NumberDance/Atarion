package com.atarion.game.interfaz.escena.online.cliente;

import com.atarion.game.entidad.jugador.Jugador;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class HiloCliente implements Runnable
{
    private Socket cliente = null;
    private Jugador estado = null;
    private Json conversor = new Json(); 
    
    
    public HiloCliente(Socket cliente,Jugador estado)
    { 
        this.cliente = cliente;
        this.estado = estado;
        
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
            cliente.getOutputStream().write(this.conversor.toJson(estado).concat("\n").getBytes());
            
            String response = new BufferedReader(new InputStreamReader(cliente.getInputStream())).readLine();
            Gdx.app.log("INFO","El server responde: " + response);
        } 
        catch (IOException ex)
        {}
    }
}
