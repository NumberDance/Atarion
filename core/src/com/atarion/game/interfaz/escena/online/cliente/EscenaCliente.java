package com.atarion.game.interfaz.escena.online.cliente;


import com.atarion.game.entidad.jugador.humano.ClaseHumano;
import com.atarion.game.entidad.jugador.humano.Humano;
import com.atarion.game.interfaz.escena.Escena;
import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.badlogic.gdx.Gdx;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class EscenaCliente extends Escena
{
    private Socket cliente;
    private MensajeJSON iniciales = null;
    private BufferedReader lector;
    
    
    public EscenaCliente()
    {
        super();
        
        try
        { 
            this.cliente = new Socket("localhost",20595); 
            this.lector = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        } 
        catch (IOException ex)
        {}
    }
    @Override
    public void entrar(ClaseHumano clase)
    {
        this.humano = this.asignarClase(humano,clase,true);
        this.humano.setIdentificador(new MensajeJSON().recibir(lector).getJson().getString("identificador"));
        
        try
        { 
            MensajeJSON estado = new MensajeJSON();
            estado.escribirAtributo("identificador",this.humano.getIdentificador());
            estado.escribirAtributo("tipo",this.humano.getClase().toString());
            estado.enviar(this.cliente.getOutputStream());
            
            this.iniciales = new MensajeJSON().recibir(lector);
            Gdx.app.log("INFO",this.iniciales.getMensaje());
            
            this.iniciales.getJson().getJSONArray("iniciales").forEach
            (
                inicial ->
                {
                    MensajeJSON valor = new MensajeJSON().recibir(inicial.toString());
                    
                    if(!valor.getJson().getString("identificador").equals(this.humano.getIdentificador()))
                    {   
                        ClaseHumano clase2 = ClaseHumano.valueOf(valor.getJson().getString("tipo"));
                        humano2 = this.asignarClase(this.humano2,clase2,false);
                        
                        String identificador2 = valor.getJson().getString("identificador");
                        humano2.setIdentificador(identificador2);
                    }
                }
            );
        } 
        catch (IOException ex)
        {}
        
        super.entrar(clase);
    }

    
    @Override
    public void show()
    { super.show(); }
    @Override
    public void render(float delta)
    {
        super.render(delta);
        new HiloCliente(this).start();
    }
    public void actualizarPartida(String estado)
    { 
        MensajeJSON mensaje = new MensajeJSON().recibir(lector);
         
        mensaje.getJson().getJSONArray("estados").forEach
        (
            elemento ->
            {
                MensajeJSON valor = new MensajeJSON().recibir(elemento.toString());
                    
                if(valor.getJson().getString("identificador").equals(this.humano.getIdentificador()))
                { humano.recibirEstado(valor.getMensaje()); }
                else if(valor.getJson().getString("identificador").equals(this.humano2.getIdentificador()))
                { humano2.recibirEstado(valor.getMensaje()); }
            
                Gdx.app.log("INFO","El server responde: " + valor.getMensaje());
            }   
        );
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
