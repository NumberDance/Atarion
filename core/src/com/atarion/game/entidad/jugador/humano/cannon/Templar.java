package com.atarion.game.entidad.jugador.humano.cannon;


import com.atarion.game.entidad.habilidad.HabilidadTemplar;
import com.atarion.game.entidad.jugador.humano.ClaseHumano;
import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class Templar extends Cannon
{
    public Templar(boolean tu,boolean batalla) 
    {
        super(tu,batalla);
        
        this.clase = ClaseHumano.TEMPLAR;
        this.textura = new Texture(Gdx.files.internal("textures/templar.png"));
    }
    
    
    @Override
    public MensajeJSON enviarEstado()
    { return super.enviarEstado().escribirAtributo("tipo","Templar"); }
    @Override
    public void recibirEstado(String estado)
    { super.recibirEstado(estado); }
    
    
    @Override
    public void activarEspecial() 
    {
        velocidad *= 4;
        fuerza *= 4;
        rebote *= 4;
        
        this.habilidad = new HabilidadTemplar(genesis,this);
    }
    @Override
    public void desactivarEspecial() 
    {
        velocidad /= 4;
        fuerza /= 4;
        rebote /= 4;
        
        this.habilidad = null;
    }
}
