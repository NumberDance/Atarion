package com.atarion.game.entidad.jugador.humano;

import com.atarion.game.entidad.jugador.Jugador;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Traveler extends Humano 
{
    public Traveler(Batch genesis) 
    {
        super(genesis);
        velocidad = 2;
        vida = 50;
        this.textura = new Texture(Gdx.files.internal("traveler.png"));
    }
    
    @Override
    public void agregarEnemigo(Jugador jugador) 
    {
        this.enemigo = jugador;
    }
    @Override
    public void jugar(Camera camara)
    {
        super.jugar(camara);
    }
    @Override
    protected void controlBordes()
    {
        if(this.y < 0) 
        {
            this.y = 800 - 20;
        }
        if(this.y > 800 - 20)
        {
            this.y = 0;
        }
        if(this.x < 0)
        {
            this.x = 1000 - 80;
        }
        if(this.x > 1000 - 80) 
        {
            this.x = 0;
        }
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
    }
    @Override
    public void desactivarEspecial() {}
}
