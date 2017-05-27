package com.atarion.game.entidad.jugador.humano;

import com.atarion.game.entidad.habilidad.HabilidadGeneric;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class DummyGeneric extends Humano 
{
    public DummyGeneric(Batch genesis)
    {
        super(genesis);
        this.textura = new Texture(Gdx.files.internal("dummygeneric.png"));
        
        this.vida = 999999999;
        this.fuerza = 0;
    }

    @Override
    public void activarEspecial()
    { this.habilidad = new HabilidadGeneric(genesis,this); }
    @Override
    public void desactivarEspecial()
    { this.habilidad = null; }
}
