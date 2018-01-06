package com.atarion.game.entidad.jugador.humano.wheel;

import com.badlogic.gdx.graphics.g2d.Batch;

public class Visionary extends Wheel
{
    public Visionary()
    { super(); }
    public Visionary(Batch genesis)
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
