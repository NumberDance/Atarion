package com.atarion.game.entidad.jugador.humano.trench;


import com.atarion.game.entidad.jugador.humano.ClaseHumano;


public class Benefactor extends Trench
{
    public Benefactor(boolean tu,boolean batalla)
    { 
        super(tu,batalla); 
        this.clase = ClaseHumano.BENEFACTOR;
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
