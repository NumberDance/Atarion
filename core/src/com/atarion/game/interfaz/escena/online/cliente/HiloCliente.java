package com.atarion.game.interfaz.escena.online.cliente;

import com.atarion.game.entidad.jugador.Jugador;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HiloCliente extends Thread
{
    private Socket cliente = null;
    private Jugador jugador = null;
    private EscenaCliente escena = null;
    private Json conversor = new Json(); 
    private BufferedReader lector = null;
    
    
    public HiloCliente(Socket cliente,Jugador jugador,EscenaCliente escena)
    { 
        try
        {
            this.cliente = cliente;
            this.jugador = jugador;
            this.escena = escena;
            
            this.conversor.setTypeName(null);
            this.conversor.setUsePrototypes(false);
            this.conversor.setIgnoreUnknownFields(true);
            this.conversor.setOutputType(JsonWriter.OutputType.json);
            this.lector = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        } 
        catch (IOException ex)
        {}
    }
    
    
    @Override
    public void run()
    {
        try
        {
            cliente.getOutputStream().write(jugador.volcarEstado().toString().concat("\n").getBytes());
            
            String response = this.lector.readLine();
            Gdx.app.log("INFO","El server responde: " + response);
            
            escena.updateGlobalStates(response);
        } 
        catch (IOException ex)
        {}
    }
}
