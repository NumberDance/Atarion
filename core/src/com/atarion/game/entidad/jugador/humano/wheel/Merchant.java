package com.atarion.game.entidad.jugador.humano.wheel;

import com.badlogic.gdx.graphics.g2d.Batch;

public class Merchant extends Wheel
{
    public Merchant(Batch genesis,boolean tu)
    {
        super(genesis,tu);
    }

    
    @Override
    public StringBuilder volcarEstado()
    { return new StringBuilder(); }
    @Override
    public void recibirEstado(String estado)
    {}
    

    @Override
    public void activarEspecial()
    {}
    @Override
    public void desactivarEspecial()
    {}
}
