package com.atarion.game.entidad.jugador.humano.wheel;


import com.atarion.game.entidad.jugador.humano.ClaseHumano;
import com.badlogic.gdx.graphics.g2d.Batch;


public class Merchant extends Wheel
{
    public Merchant(boolean tu)
    { 
        super(tu); 
        this.clase = ClaseHumano.MERCHANT;
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
