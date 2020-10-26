package com.atarion.game.entidad.jugador.humano.wheel;


import com.atarion.game.entidad.jugador.humano.Humano;
import com.badlogic.gdx.Gdx;


public abstract class Wheel extends Humano
{
    public Wheel(boolean tu,boolean batalla)
    {
        super(tu,batalla);
        
        this.velocidad *= 2;
        this.fuerza /= 2;
    }

    
    @Override
    protected void controlBordes()
    {
        if(this.y < 0) 
        { this.y = Gdx.graphics.getHeight() - 20; }
        
        if(this.y > Gdx.graphics.getHeight() - 20)
        { this.y = 0; }
        
        if(this.x < 0)
        { this.x = Gdx.graphics.getWidth() - 80; }
        
        if(this.x > Gdx.graphics.getWidth() - 80) 
        { this.x = 0; }
    }
    
    
    @Override
    public abstract void activarEspecial();
    @Override
    public abstract void desactivarEspecial();
}
