package com.atarion.game.entidad.jugador.humano.wheel;


import com.atarion.game.entidad.jugador.humano.ClaseHumano;


public class Merchant extends Wheel
{
    public Merchant(boolean tu,boolean batalla)
    { 
        super(tu,batalla); 
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
