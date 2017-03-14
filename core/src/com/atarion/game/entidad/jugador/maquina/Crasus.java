package com.atarion.game.entidad.jugador.maquina;

import com.atarion.game.entidad.jugador.Jugador;
import com.atarion.game.entidad.jugador.humano.Humano;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Crasus extends Maquina
{
    public Crasus(Batch genesis) 
    {
        super(genesis);
        this.vida *= 2;
        this.textura = new Texture(Gdx.files.internal("crasus.png"));
    }

    @Override
    public void agregarEnemigo(Jugador jugador) 
    {
        this.enemigo = jugador;
        this.huir();
    }

    private void huir() 
    {

    }
    
    @Override
    public void controlBordes() 
    {   
        if(this.y < 0) 
        {
            this.y = 0;
            this.vida -= 10;
            decision = 3;
            Gdx.app.log("INFO: ", "a la bola le quedan " + this.vida + " vidas.");
        } 
        if(this.y > 800 - 100)
        {
            this.y = 800 - 100;
            this.vida -= 10;
            decision = 4;
            Gdx.app.log("INFO: ", "a la bola le quedan " + this.vida + " vidas.");
        }
        if(this.x < 0)
        {
            this.x = 0;
            this.vida -= 10;
            decision = 1;
            Gdx.app.log("INFO: ", "a la bola le quedan " + this.vida + " vidas.");
        }
        if(this.x > 1000 - 100) 
        {
            this.x = 1000 - 100;
            this.vida -= 10;
            decision = 2;
            Gdx.app.log("INFO: ", "a la bola le quedan " + this.vida + " vidas.");
        }
        if(this.y > 200 || this.y < 1000 - 200)
        {
            decision = 0;
        }
    }
    
    @Override
    public void activarEspecial() 
    {
        this.textura = new Texture(Gdx.files.internal("control.png"));
        ((Humano)this.enemigo).setInvertido(true);
    }
    @Override
    public void desactivarEspecial() 
    {
        this.textura = new Texture(Gdx.files.internal("crasus.png"));
        ((Humano)this.enemigo).setInvertido(false);
    } 
}
