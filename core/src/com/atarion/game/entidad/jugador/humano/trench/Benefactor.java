package com.atarion.game.entidad.jugador.humano.trench;

import com.badlogic.gdx.graphics.g2d.Batch;

public class Benefactor extends Trench
{
    public Benefactor()
    { super(); }
    public Benefactor(Batch genesis)
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
