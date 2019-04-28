package com.atarion.game.entidad.jugador.humano.trench;


import com.atarion.game.entidad.jugador.humano.ClaseHumano;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class DummyTrench extends Trench
{
    public DummyTrench()
    {
        super(true,true);
        
        this.clase = ClaseHumano.DUMMYTRENCH;
        this.textura = new Texture(Gdx.files.internal("dummytrench.png"));
        
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
