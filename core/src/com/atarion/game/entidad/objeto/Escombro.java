package com.atarion.game.entidad.objeto;


import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;


public class Escombro extends Objeto 
{
    private float velocidad;
    protected boolean destino;
    
    
    public Escombro(Batch genesis) 
    {       
        super(genesis,new Texture(Gdx.files.internal("textures/debris.png")),200);
        
        this.x = (float) (Math.random() * 1000 + 1);
        this.y = 800 + 100;
        
        this.width = 100;
        this.height = 100;
        
        velocidad = 1f;
    }
    
    
    @Override
    public MensajeJSON enviarEstado()
    { 
        MensajeJSON estado = new MensajeJSON();
        estado.escribirAtributo("velocidad","" + this.velocidad);
        estado.escribirAtributo("destino","" + this.destino);
        
        return estado; 
    }
    @Override
    public void recibirEstado(String estado)
    { super.recibirEstado(estado); }
    
    
    public void caer()
    {
        if(this.y == 0 - 100)
        { destino = true; }
        else
        { this.y -= 200 * Gdx.graphics.getDeltaTime() * this.velocidad; }
    }
    public boolean isDestino() 
    { return destino; }
}
