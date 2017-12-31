package com.atarion.game.entidad.jugador.humano.trench;

import com.badlogic.gdx.graphics.g2d.Batch;

public class Avenger extends Trench
{
    public Avenger(Batch genesis,boolean tu)
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
