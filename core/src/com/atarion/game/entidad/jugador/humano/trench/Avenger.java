package com.atarion.game.entidad.jugador.humano.trench;


import com.atarion.game.entidad.jugador.humano.ClaseHumano;
import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.atarion.game.interfaz.escena.online.ParteMensaje;
import com.badlogic.gdx.graphics.g2d.Batch;


public class Avenger extends Trench
{
    public Avenger(boolean tu)
    { 
        super(tu); 
        this.clase = ClaseHumano.AVENGER;
    }

    
    @Override
    public MensajeJSON enviarEstado()
    { return super.enviarEstado().escribirAtributo(null,null,ParteMensaje.FINAL); }
    @Override
    public void recibirEstado(String estado)
    { super.recibirEstado(estado); }
    

    @Override
    public void activarEspecial()
    {}
    @Override
    public void desactivarEspecial()
    {}
}
