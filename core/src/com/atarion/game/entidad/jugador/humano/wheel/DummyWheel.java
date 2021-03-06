package com.atarion.game.entidad.jugador.humano.wheel;


import com.atarion.game.entidad.jugador.humano.ClaseHumano;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class DummyWheel extends Wheel
{
    public DummyWheel()
    {
        super(true,true);
        
        this.clase = ClaseHumano.DUMMYWHEEL;
        this.textura = new Texture(Gdx.files.internal("textures/dummywheel.png"));
        
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
