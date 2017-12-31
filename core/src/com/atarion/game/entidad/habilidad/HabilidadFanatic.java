package com.atarion.game.entidad.habilidad;

import com.atarion.game.entidad.jugador.Jugador;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class HabilidadFanatic extends Habilidad
{
    public HabilidadFanatic(Batch genesis, Jugador jugador)
    {
        super(genesis, jugador);
        this.textura = new Texture(Gdx.files.internal("fanaticbomb.png"));
        
        jugador.setFuerza(150);
    }
    
    
    @Override
    public StringBuilder volcarEstado()
    { return new StringBuilder(); }
    @Override
    public void recibirEstado(String estado)
    {}
    
    
    @Override
    public void actualizarEstado()
    { genesis.draw(textura,jugador.getX() - 110,jugador.getY() - 110); }
}
