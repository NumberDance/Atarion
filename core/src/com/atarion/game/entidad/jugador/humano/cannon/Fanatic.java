package com.atarion.game.entidad.jugador.humano.cannon;


import com.atarion.game.entidad.habilidad.HabilidadFanatic;
import com.atarion.game.entidad.jugador.humano.ClaseHumano;
import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.atarion.game.interfaz.escena.online.ParteMensaje;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;


public class Fanatic extends Cannon
{
    public Fanatic(boolean tu)
    {
        super(tu);
        
        this.clase = ClaseHumano.FANATIC;
        this.textura = new Texture(Gdx.files.internal("fanatic.png"));
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
        int azar = (int) (Math.random() * 2 + 1);
        
        if(azar == 1)
        { this.vida = 0; }
        else if(azar == 2)
        { 
            this.vida = 1;
            this.recarga = 0;
        }
        
        this.habilidad = new HabilidadFanatic(genesis,this);
    }
    @Override
    public void desactivarEspecial()
    {}
}
