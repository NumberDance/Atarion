package com.atarion.game.entidad.jugador.humano.cannon;

import com.atarion.game.entidad.jugador.humano.cannon.Cannon;
import com.atarion.game.entidad.habilidad.HabilidadTemplar;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Templar extends Cannon
{   
    public Templar(Batch genesis,boolean tu) 
    {
        super(genesis,tu);
        this.textura = new Texture(Gdx.files.internal("templar.png"));
    }
    
    
    @Override
    public void activarEspecial() 
    {
        velocidad *= 4;
        fuerza *= 4;
        rebote *= 4;
        
        this.habilidad = new HabilidadTemplar(genesis,this);
    }
    @Override
    public void desactivarEspecial() 
    {
        velocidad /= 4;
        fuerza /= 4;
        rebote /= 4;
        
        this.habilidad = null;
    }
}
