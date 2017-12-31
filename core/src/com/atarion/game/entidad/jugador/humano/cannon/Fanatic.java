package com.atarion.game.entidad.jugador.humano.cannon;

import com.atarion.game.entidad.habilidad.HabilidadFanatic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Fanatic extends Cannon
{   
    public Fanatic(Batch genesis,boolean tu)
    {
        super(genesis,tu);
        this.textura = new Texture(Gdx.files.internal("fanatic.png"));
    }

    
    @Override
    public StringBuilder volcarEstado()
    { return new StringBuilder(); }
    @Override
    public void recibirEstado(String estado)
    {}
    

    @Override
    public void activarEspecial()
    {
        int azar = (int) (Math.random() * 2 + 1);
        
        if(azar == 1)
        { this.vida = 0; }
        else if(azar == 2)
        { 
            this.vida = 1;
            this.recarga = 0;
        }
        
        this.habilidad = new HabilidadFanatic(genesis,this);
    }
    @Override
    public void desactivarEspecial()
    {}
}
