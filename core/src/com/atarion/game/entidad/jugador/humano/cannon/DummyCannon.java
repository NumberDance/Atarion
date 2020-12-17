package com.atarion.game.entidad.jugador.humano.cannon;


import com.atarion.game.entidad.jugador.humano.ClaseHumano;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class DummyCannon extends Cannon
{
    public DummyCannon()
    {
        super(true,true);
        
        this.clase = ClaseHumano.DUMMYCANNON;
        this.textura = new Texture(Gdx.files.internal("textures/dummycannon.png"));
        
        this.vida = 999999999;
        this.fuerza = 0;
    }

    
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
