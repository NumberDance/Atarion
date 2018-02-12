package com.atarion.game.interfaz.escena.online.cliente;


import com.atarion.game.entidad.jugador.humano.Humano;
import com.atarion.game.entidad.jugador.humano.wheel.Traveler;
import com.atarion.game.interfaz.escena.Escena;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import java.io.PrintWriter;
import java.util.HashSet;


public class EscenaCliente extends Escena
{
    private PrintWriter salida; 
    private HashSet<Humano> enemigos = new HashSet<Humano>();
    private HashSet<Humano> aliados = new HashSet<Humano>();
    private String idhumano;
    private BitmapFont texto;
    private boolean listo = false;
    private HiloCliente hilo;
    
    
    public EscenaCliente()
    { super(null); }

    
    @Override
    public void show()
    {
        /*try
        {*/
            super.show();
            
            this.hilo = new HiloCliente(humano,this);
            
            humano = new Traveler(genesis);
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
    }
    @Override
    public void render(float delta)
    {
        super.render(delta);
        hilo.start();
    }
    
    
    public void updateGlobalStates(String states)
    { humano2.recibirEstado(states); }
    public void comenzarPartida(String identificador, String estado)
    { 
        this.idhumano = identificador; 
        this.listo = true;
        
        Gdx.app.log("INFO","Asignado identificador: " + this.idhumano);
        Gdx.app.log("INFO","Estado inicial de la partida: " + estado);
    }
}
