package com.atarion.game.entidad.jugador.humano.wheel;


import com.atarion.game.entidad.jugador.humano.Humano;


public abstract class Wheel extends Humano
{
    public Wheel(boolean tu)
    {
        super(tu);
        
        this.velocidad *= 2;
        this.fuerza /= 2;
    }

    
    @Override
    protected void controlBordes()
    {
        if(this.y < 0) 
        { this.y = 800 - 20; }
        
        if(this.y > 800 - 20)
        { this.y = 0; }
        
        if(this.x < 0)
        { this.x = 1000 - 80; }
        
        if(this.x > 1000 - 80) 
        { this.x = 0; }
    }
    
    
    @Override
    public abstract void activarEspecial();
    @Override
    public abstract void desactivarEspecial();
}
