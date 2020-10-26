package com.atarion.game.interfaz.escena.online.cliente;

import com.atarion.game.entidad.jugador.Jugador;
import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HiloCliente extends Thread {
    
    private Socket cliente;
    private BufferedReader lector;
    private EscenaCliente escena;
    private Json conversor = new Json();
    
    public HiloCliente(EscenaCliente escena) {
        this.cliente = escena.getCliente();
        this.lector = escena.getLector();
        
        this.escena = escena;
        
        this.conversor.setTypeName(null);
        this.conversor.setUsePrototypes(false);
        this.conversor.setIgnoreUnknownFields(true);
        this.conversor.setOutputType(JsonWriter.OutputType.json);
    }
    
    @Override
    public void run() {
        try {
            MensajeJSON estados = new MensajeJSON();
            
            estados.concatenar(this.escena.getTu().enviarEstado(), "estados");
            
            estados = this.obtenerEstados(this.escena.getHumanosEnemigos().stream()
                    .map(humano -> (Jugador) humano), estados);
            estados = this.obtenerEstados(this.escena.getHumanosAliados().stream()
                    .map(humano -> (Jugador) humano), estados);
            
            estados = this.obtenerEstados(this.escena.getMaquinasEnemigas().stream()
                    .map(maquina -> (Jugador) maquina), estados);
            estados = this.obtenerEstados(this.escena.getMaquinasAliadas().stream()
                    .map(maquina -> (Jugador) maquina), estados);
            
            //Ojo: causa problemas de memoria en partidas largas
            //Gdx.app.log("INFO: El cliente envia: ",estados.getMensaje());
            estados.enviar(cliente.getOutputStream());
            escena.actualizarPartida(this.lector.readLine());
        } catch (IOException ex) {
        }
    }
    
    private MensajeJSON obtenerEstados(Stream<Jugador> jugadores, MensajeJSON estados) {
        for (Jugador jugador : jugadores.collect(Collectors.toList())) {
            if (jugador.isInteraccion()) {
                estados = estados.concatenar(jugador.enviarEstado(), "estados");
                jugador.setInteraccion(false);
            }
        }
        
        return estados;
    }
}
