package com.atarion.game.entidad.jugador.humano.wheel;


import com.atarion.game.entidad.jugador.humano.ClaseHumano;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;


public class DummyWheel extends Wheel
{
    public DummyWheel(boolean tu)
    {
        super(tu);
        
        this.clase = ClaseHumano.DUMMYWHEEL;
        this.textura = new Texture(Gdx.files.internal("dummywheel.png"));
        
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
