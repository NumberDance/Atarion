package com.atarion.game.entidad.habilidad;


import com.atarion.game.entidad.jugador.Jugador;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;


public class HabilidadGeneric extends Habilidad
{
    public HabilidadGeneric(Batch genesis, Jugador jugador)
    {
        super(genesis, jugador);
        this.textura = new Texture(Gdx.files.internal("textures/genericspecial.png"));
    }
    
    
    @Override
    public void recibirEstado(String estado)
    {}
}
