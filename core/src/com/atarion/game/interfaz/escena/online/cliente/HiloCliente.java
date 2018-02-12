package com.atarion.game.interfaz.escena.online.cliente;

import com.atarion.game.entidad.jugador.Jugador;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class HiloCliente extends Thread
{
    private Socket cliente;
    private Jugador jugador;
    private EscenaCliente escena;
    private Json conversor = new Json(); 
    private BufferedReader lector;
    
    
    public HiloCliente(Jugador jugador,EscenaCliente escena)
    { 
        try
        {
            cliente = new Socket("192.168.1.100",20595);
            this.lector = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            
            this.jugador = jugador;
            this.escena = escena;
            
            this.conversor.setTypeName(null);
            this.conversor.setUsePrototypes(false);
            this.conversor.setIgnoreUnknownFields(true);
            this.conversor.setOutputType(JsonWriter.OutputType.json);
            
            escena.comenzarPartida(this.lector.readLine(),this.lector.readLine());
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
