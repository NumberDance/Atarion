package com.atarion.game.entidad.habilidad;

import com.atarion.game.entidad.jugador.Jugador;
import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.atarion.game.interfaz.escena.online.ParteMensaje;
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
    public MensajeJSON enviarEstado()
    { return super.enviarEstado().escribirAtributo(null,null,ParteMensaje.FINAL); }
    @Override
    public void recibirEstado(String estado)
    {}
    
    
    @Override
    public void actualizarEstado()
    { genesis.draw(textura,jugador.getX() - 110,jugador.getY() - 110); }
}
