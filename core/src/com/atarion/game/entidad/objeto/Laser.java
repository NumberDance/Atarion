package com.atarion.game.entidad.objeto;

import com.atarion.game.entidad.jugador.Jugador;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Laser extends Objeto
{
    private Jugador jugador = null;
    
    
    public Laser(Batch genesis,String texture,Jugador jugador) 
    {
        super(genesis,new Texture(Gdx.files.internal(texture)),20);
        
        this.width = 50;
        this.height = 800;
        this.jugador = jugador;       
    }
    
    
    @Override
    public void actualizarEstado()
    {
        this.x = jugador.getX() + 25;
        
        for(int i = 0; i < 800; i += 50)
        {
            if(i > jugador.getY() + 50 || i < jugador.getY())
            { genesis.draw(textura, x, i); }
        }
    }
}
