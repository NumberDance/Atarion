package com.atarion.game.entidad.jugador.humano.wheel;


import com.atarion.game.entidad.habilidad.HabilidadTraveler;
import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.atarion.game.interfaz.escena.online.ParteMensaje;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;


public class Traveler extends Wheel
{
    public Traveler(Batch genesis,boolean tu) 
    {
        super(genesis,tu);
        this.textura = new Texture(Gdx.files.internal("traveler.png"));
    }

    
    @Override
    public MensajeJSON enviarEstado()
    { return super.enviarEstado().escribirAtributo(null,null,ParteMensaje.FINAL); }
    @Override
    public void recibirEstado(String estado)
    { super.recibirEstado(estado); }
    
    
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
