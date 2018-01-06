package com.atarion.game.entidad.jugador.humano.wheel;

import com.badlogic.gdx.graphics.g2d.Batch;

public class Merchant extends Wheel
{
    public Merchant()
    { super(); }
    public Merchant(Batch genesis)
    { super(genesis); }

    
    @Override
    public StringBuilder volcarEstado()
    { return super.volcarEstado().append("}"); }
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
