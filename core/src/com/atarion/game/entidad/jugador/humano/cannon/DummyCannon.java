package com.atarion.game.entidad.jugador.humano.cannon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class DummyCannon extends Cannon
{
    public DummyCannon(Batch genesis,boolean tu)
    {
        super(genesis,tu);
        this.textura = new Texture(Gdx.files.internal("dummycannon.png"));
        
        this.vida = 999999999;
        this.fuerza = 0;
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
