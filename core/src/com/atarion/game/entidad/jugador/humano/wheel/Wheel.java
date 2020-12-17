package com.atarion.game.entidad.jugador.humano.wheel;


import com.atarion.game.entidad.jugador.humano.Humano;
import com.atarion.game.interfaz.escena.Escena;


public abstract class Wheel extends Humano
{
    public Wheel(boolean tu,boolean batalla)
    {
        super(tu,batalla);
        
        this.velocidad *= 2;
        this.fuerza /= 2;
    }

    
    @Override
    protected void controlBordes(Escena escena)
    {
        if(this.y < 0) 
        { this.y = escena.getTotalHeight() - 20; }
        
        if(this.y > escena.getTotalHeight() - 20)
        { this.y = 0; }
        
        if(this.x < 0)
        { this.x = escena.getTotalWidth() - 80; }
        
        if(this.x > escena.getTotalWidth() - 80) 
        { this.x = 0; }
    }
    
    
    @Override
    public abstract void activarEspecial();
    @Override
    public abstract void desactivarEspecial();
}
