package com.atarion.game.entidad.jugador.maquina;

import com.atarion.game.entidad.jugador.Jugador;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Dummy extends Maquina
{
    public Dummy(Batch genesis) 
    {
        super(genesis);
        this.y = 400;
        
        this.textura = new Texture(Gdx.files.internal("dummy.png"));
        this.vida = 999999999;
        this.fuerza = 0;
    }

    @Override
    protected void controlBordes()
    {
        if(this.y < 0) 
        { this.y = 800 - 150; } 
        else if(this.y > 800 - 100)
        { this.y = 0 + 150; }
        else if(this.x < 0)
        { this.x = 1000 - 150; }
        else if(this.x > 1000 - 100) 
        { this.x = 0 + 150; }
    }

    @Override
    public void agregarEnemigo(Jugador jugador)
    {}

    @Override
    public void activarEspecial()
    {}
    @Override
    public void desactivarEspecial()
    {}
}
