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
