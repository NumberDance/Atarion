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
    private HashSet<Humano> enemigos = new HashSet<Humano>();
    private HashSet<Humano> aliados = new HashSet<Humano>();
    private String idhumano;
    private Clase clase = null;
    private JSONObject inicial = null;
    private BufferedReader lector;
    
    
    public EscenaCliente(Clase clase)
    {
        super(null);
        
        try
        { 
            this.cliente = new Socket("192.168.1.104",20595); 
            this.lector = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            this.clase = clase;
            
            this.idhumano = new MensajeJSON().recibir(lector).getJson().getString("identificador");
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
            
        switch(this.clase)
        {
            case ANARCHIST:
                humano2 = new Anarchist();
            break;
            case FANATIC:
                humano2 = new Fanatic();
            break;
            case TEMPLAR:
                humano2 = new Templar();
            break;
                
            case AVENGER:
                humano2 = new Avenger();
            break;
            case BENEFACTOR:
                humano2 = new Benefactor();
            break;
            case GUARDIAN:
                humano2 = new Guardian();
            break;
                
            case MERCHANT:
                humano2 = new Merchant();
            break;
            case TRAVELER:
                humano2 = new Traveler();
            break;
            case VISIONARY:
                humano2 = new Visionary();
            break;
        }
        humano2.setGenesis(genesis);

        humano.agregarEnemigo(humano2);
        humano2.agregarEnemigo(humano);
    }
    @Override
    public void render(float delta)
    {
        super.render(delta);
        new HiloCliente(this).start();
    }
    public void actualizarPartida(String estado)
    { 
        MensajeJSON mensaje = new MensajeJSON();
        mensaje.recibir(lector);
            
        if(mensaje.getJson().getString("identificador").equals(this.humano.getIdentificador()))
        { humano.recibirEstado(estado); }
        else if(mensaje.getJson().getString("identificador").equals(this.humano2.getIdentificador()))
        { humano2.recibirEstado(estado); }
        else if(mensaje.getJson().getString("identificador").equals(this.maquina.getIdentificador()))
        { maquina.recibirEstado(estado); }
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
