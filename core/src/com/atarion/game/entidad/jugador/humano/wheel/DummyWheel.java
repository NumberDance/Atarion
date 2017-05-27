package com.atarion.game.entidad.jugador.humano.wheel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class DummyWheel extends Wheel
{
    public DummyWheel(Batch genesis)
    {
        super(genesis);
        this.textura = new Texture(Gdx.files.internal("dummywheel.png"));
        
        this.vida = 999999999;
        this.fuerza = 0;
    }

    @Override
    public void activarEspecial()
    {}
    @Override
    public void desactivarEspecial()
    {}
}
