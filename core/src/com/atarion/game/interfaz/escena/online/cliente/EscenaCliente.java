package com.atarion.game.interfaz.escena.online.cliente;


import com.atarion.game.entidad.jugador.humano.Humano;
import com.atarion.game.entidad.jugador.humano.cannon.Anarchist;
import com.atarion.game.entidad.jugador.humano.cannon.Fanatic;
import com.atarion.game.entidad.jugador.humano.cannon.Templar;
import com.atarion.game.entidad.jugador.humano.trench.Avenger;
import com.atarion.game.entidad.jugador.humano.trench.Benefactor;
import com.atarion.game.entidad.jugador.humano.trench.Guardian;
import com.atarion.game.entidad.jugador.humano.wheel.Merchant;
import com.atarion.game.entidad.jugador.humano.wheel.Traveler;
import com.atarion.game.entidad.jugador.humano.wheel.Visionary;
import com.atarion.game.interfaz.escena.Escena;
import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.atarion.game.interfaz.escena.online.ParteMensaje;
import com.atarion.game.interfaz.escena.online.servidor.Clase;
import com.badlogic.gdx.Gdx;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;
import org.json.JSONObject;


public class EscenaCliente extends Escena
{
    private Socket cliente;
    private PrintWriter salida; 
    private HashSet<Humano> enemigos = new HashSet<>();
    private HashSet<Humano> aliados = new HashSet<>();
    private String idhumano;
    private JSONObject inicial = null;
    private BufferedReader lector;
    
    
    public EscenaCliente()
    {
        super(null);
        
        try
        { 
            this.cliente = new Socket("localhost",20595); 
            this.lector = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            
            this.idhumano = new MensajeJSON().recibir(lector).getJson().getString("identificador");
            Gdx.app.log("INFO","Mi ID es: " + this.idhumano);
            new MensajeJSON().escribirAtributo("tipo",this.clase.toString(),ParteMensaje.SINGULAR).enviar(this.cliente.getOutputStream());
            this.inicial = new MensajeJSON().recibir(lector).getJson();
        } 
        catch (IOException ex)
        {}
    }

    
    @Override
    public void show()
    {
        super.show();
            
        this.humano.setIdentificador(this.idhumano);
        this.humano2 = new Traveler(this.genesis,false);

        this.humano.agregarEnemigo(this.humano2);
        this.humano2.agregarEnemigo(this.humano);
    }
    @Override
    public void render(float delta)
    {
        super.render(delta);
        new HiloCliente(this).start();
    }
    public void actualizarPartida(String estado)
    { 
        //MensajeJSON mensaje = new MensajeJSON().recibir(lector);
            
        if(!estado.equals(""))//if(mensaje.getJson() != null)
        {
            /*if(mensaje.getJson().getString("identificador").equals(this.humano.getIdentificador()))
            { humano.recibirEstado(estado); }*/
            //else if(mensaje.getJson().getString("identificador").equals(this.humano2.getIdentificador()))
            /*{*/ humano2.recibirEstado(estado); /*}*/
            /*else if(mensaje.getJson().getString("identificador").equals(this.maquina.getIdentificador()))
            { maquina.recibirEstado(estado); }*/
            
            Gdx.app.log("INFO","El server responde: " + estado);
        }
    }

    
    public Socket getCliente()
    { return cliente; }
    public Humano getHumano()
    { return humano; }
    public BufferedReader getLector()
    { return lector; }
    public Humano getHumano2()
    { return humano2; }
}
