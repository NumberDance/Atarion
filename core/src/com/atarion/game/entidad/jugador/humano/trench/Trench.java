package com.atarion.game.entidad.jugador.humano.trench;

import com.atarion.game.entidad.jugador.humano.Humano;
import com.atarion.game.entidad.objeto.Objeto;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class Trench extends Humano
{   
    public Trench(Batch genesis)
    {
        super(genesis);
        
        this.vida *= 2;
        this.velocidad /= 2;
        this.inmune = true;
    }

    
    @Override
    public void colisionObjeto(Objeto objeto)
    {
        if(this.inmune)
        { Gdx.app.log("INFO","Las barreras de la clase Trench pueden atravesar objetos."); }
        else
        {
            Gdx.app.log("INFO","De repente, te hiciste vulnerable a los objetos. 100 puntos de daño y te quedan " + this.getVida() + "vidas");
            this.vida -= 100;
        }
    }
    
    
    @Override
    public abstract void activarEspecial();
    @Override
    public abstract void desactivarEspecial();
}
