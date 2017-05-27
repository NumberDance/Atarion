package com.atarion.game.entidad.jugador.humano.wheel;

import com.atarion.game.entidad.jugador.humano.wheel.Wheel;
import com.atarion.game.entidad.habilidad.HabilidadTraveler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Traveler extends Wheel
{   
    public Traveler(Batch genesis) 
    {
        super(genesis);
        this.textura = new Texture(Gdx.files.internal("traveler.png"));
    }

    
    @Override
    public void activarEspecial() 
    {
        float xenemigo = enemigo.getX();
        float yenemigo = enemigo.getY();
        
        enemigo.setX(this.x);
        enemigo.setY(this.y);
        
        this.x = xenemigo;
        this.y = yenemigo;
        
        this.habilidad = new HabilidadTraveler(genesis,this);
    }
    @Override
    public void desactivarEspecial() 
    { this.habilidad = null; }
}