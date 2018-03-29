package com.atarion.game.entidad.objeto;


import com.atarion.game.entidad.Entidad;
import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;


public abstract class Objeto extends Entidad 
{
    protected int dureza = 0;
    
    
    protected Objeto(Batch genesis, Texture textura, int dureza)
    {
        this.genesis = genesis;
        this.textura = textura;
        this.dureza = dureza;
    }
    
    
    @Override
    public MensajeJSON enviarEstado()
    { return super.enviarEstado().escribirAtributo("dureza","" + this.dureza); }
    
    
    public int getDureza() 
    { return dureza; }
}
