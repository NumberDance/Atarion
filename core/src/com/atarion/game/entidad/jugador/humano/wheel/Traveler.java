package com.atarion.game.entidad.jugador.humano.wheel;


import com.atarion.game.entidad.habilidad.HabilidadTraveler;
import com.atarion.game.entidad.jugador.humano.ClaseHumano;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class Traveler extends Wheel
{
    public Traveler(boolean tu,boolean batalla) 
    {
        super(tu,batalla);
        
        this.clase = ClaseHumano.TRAVELER;
        this.textura = new Texture(Gdx.files.internal("traveler.png"));
    }

    
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
        
        this.habilidad = new HabilidadTraveler(this.genesis,this);
        enemigo.setInteraccion(true);
    }
    @Override
    public void desactivarEspecial() 
    { this.habilidad = null; }
}
