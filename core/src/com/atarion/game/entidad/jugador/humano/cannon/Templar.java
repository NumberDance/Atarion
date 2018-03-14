package com.atarion.game.entidad.jugador.humano.cannon;


import com.atarion.game.entidad.habilidad.HabilidadTemplar;
import com.atarion.game.entidad.jugador.humano.ClaseHumano;
import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.atarion.game.interfaz.escena.online.ParteMensaje;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;


public class Templar extends Cannon
{
    public Templar(boolean tu) 
    {
        super(tu);
        
        this.clase = ClaseHumano.TEMPLAR;
        this.textura = new Texture(Gdx.files.internal("templar.png"));
    }
    
    
    @Override
    public MensajeJSON enviarEstado()
    { return super.enviarEstado().escribirAtributo("tipo","Templar",ParteMensaje.FINAL); }
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
