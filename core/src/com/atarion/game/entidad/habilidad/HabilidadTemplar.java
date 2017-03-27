package com.atarion.game.entidad.habilidad;

import com.atarion.game.entidad.jugador.Jugador;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class HabilidadTemplar extends Habilidad
{
    public HabilidadTemplar(Batch genesis, Jugador jugador) 
    {
        super(genesis, jugador);
        this.textura = new Texture(Gdx.files.internal("templarsword.png"));
    }
}
