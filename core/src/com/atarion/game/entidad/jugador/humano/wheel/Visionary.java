package com.atarion.game.entidad.jugador.humano.wheel;


import com.atarion.game.entidad.jugador.humano.ClaseHumano;
import com.badlogic.gdx.graphics.g2d.Batch;


public class Visionary extends Wheel
{
    public Visionary(boolean tu,boolean batalla)
    { 
        super(tu,batalla); 
        this.clase = ClaseHumano.VISIONARY;
    }

    
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
