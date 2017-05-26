package com.atarion.game.entidad.habilidad;

import com.atarion.game.entidad.Entidad;
import com.atarion.game.entidad.jugador.Jugador;
import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class Habilidad extends Entidad 
{
    protected Jugador jugador = null;
    
    
    public Habilidad(Batch genesis,Jugador jugador)
    {
        this.jugador = jugador;
        this.genesis = genesis;
    }
    
    
    @Override
    public void actualizarEstado()
    { genesis.draw(textura,jugador.getX(),jugador.getY() - 30); }
}
