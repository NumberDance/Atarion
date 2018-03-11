package com.atarion.game.interfaz.escena.online.cliente;


import com.atarion.game.entidad.jugador.humano.Humano;
import com.atarion.game.entidad.jugador.humano.wheel.Traveler;
import com.atarion.game.interfaz.escena.Escena;
import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.atarion.game.interfaz.escena.online.ParteMensaje;
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
    private JSONObject iniciales = null;
    private BufferedReader lector;
    
    
    public EscenaCliente()
    {
        super();
        
        try
        { 
            this.cliente = new Socket("localhost",20595); 
            this.lector = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            
            this.idhumano = new MensajeJSON().recibir(lector).getJson().getString("identificador");
        } 
        catch (IOException ex)
        {}
    }

    
    @Override
    public void show()
    {
        super.show();
        this.humano.setIdentificador(this.idhumano);
        
        try
        { 
            MensajeJSON estado = new MensajeJSON();
            estado.escribirAtributo("identificador",this.idhumano,ParteMensaje.PRINCIPIO);
            estado.escribirAtributo("tipo",this.clase.toString(),ParteMensaje.FINAL);
            estado.enviar(this.cliente.getOutputStream());
            
            this.iniciales = new MensajeJSON().recibir(lector).getJson();
            Gdx.app.log("INFO",this.iniciales.toString());
        } 
        catch (IOException ex)
        {}
            
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
