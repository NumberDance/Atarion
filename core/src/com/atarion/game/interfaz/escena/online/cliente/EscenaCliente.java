package com.atarion.game.interfaz.escena.online.cliente;


import com.atarion.game.entidad.jugador.humano.Humano;
import com.atarion.game.entidad.jugador.humano.wheel.Traveler;
import com.atarion.game.interfaz.escena.Escena;
import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.badlogic.gdx.Gdx;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;


public class EscenaCliente extends Escena
{
    private Socket cliente;
    private PrintWriter salida; 
    private HashSet<Humano> enemigos = new HashSet<Humano>();
    private HashSet<Humano> aliados = new HashSet<Humano>();
    private String idhumano, inicial;
    private BufferedReader lector;
    
    
    public EscenaCliente()
    {
        super(null);
        
        try
        { 
            this.cliente = new Socket("192.168.1.102",20595); 
            this.lector = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            
            this.idhumano = this.lector.readLine();
            humano = new Traveler(genesis);
            humano.enviarEstado().enviar(cliente.getOutputStream());
            this.inicial = this.lector.readLine();
        } 
        catch (IOException ex)
        {}
    }

    
    @Override
    public void show()
    {
        /*try
        {*/
            super.show();
  
            humano.setIdentificador(idhumano);
            /*cliente.getOutputStream().write("Traveler".concat("\n").getBytes());
            
            String inicial = new BufferedReader(new InputStreamReader(cliente.getInputStream())).readLine();
            switch(inicial)
            {
                case "Anarchist":
                    humano2 = new Anarchist();
                break;
                case "Fanatic":
                    humano2 = new Fanatic();
                break;
                case "Templar":
                    humano2 = new Templar();
                break;
                
                case "Avenger":
                    humano2 = new Avenger();
                break;
                case "Benefactor":
                    humano2 = new Benefactor();
                break;
                case "Guardian":
                    humano2 = new Guardian();
                break;
                
                case "Merchant":
                    humano2 = new Merchant();
                break;
                case "Traveler":*/
                    humano2 = new Traveler();
                /*break;
                case "Visionary":
                    humano2 = new Visionary();
                break;
            }*/
            humano2.setGenesis(genesis);
        /*} 
        catch (IOException ex)
        {}*/
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
