package com.atarion.game.entidad.jugador.humano.trench;


import com.atarion.game.entidad.jugador.humano.ClaseHumano;


public class Avenger extends Trench
{
    public Avenger(boolean tu,boolean batalla)
    { 
        super(tu,batalla); 
        this.clase = ClaseHumano.AVENGER;
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
