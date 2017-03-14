package com.atarion.game.entidad.jugador.humano;

import com.atarion.game.entidad.objeto.Objeto;
import com.atarion.game.entidad.jugador.Jugador;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Guardian extends Humano
{
    public Guardian(Batch genesis) 
    {
        super(genesis);
        this.textura = new Texture(Gdx.files.internal("guardian.png"));
        this.vida = 200;
    }
    
    @Override
    public void agregarEnemigo(Jugador jugador) 
    {
        enemigo = jugador;
    }
    
    @Override
    public void colisionObjeto(Objeto objeto)
    {
        Gdx.app.log("INFO","Las barreras de la clase Guardian pueden atravesar objetos.");
    }
    
    @Override
    public void activarEspecial() 
    {
        if(enemigo.isActivado())
        {
            enemigo.pararEspecial();
        }
        else
        {
            Gdx.app.log("ERROR","No hay un módulo activado que cancelar.");
            this.activado = false;
        }
    }
    @Override
    public void desactivarEspecial() {}
}
