package com.atarion.game.entidad.jugador.humano.trench;


import com.atarion.game.entidad.habilidad.HabilidadGuardian;
import com.atarion.game.entidad.jugador.humano.ClaseHumano;
import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.atarion.game.interfaz.escena.online.ParteMensaje;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Guardian extends Trench
{
    public Guardian(boolean tu) 
    {
        super(tu);
        
        this.clase = ClaseHumano.GUARDIAN;
        this.textura = new Texture(Gdx.files.internal("guardian.png"));
    }
    
    
    @Override
    public MensajeJSON enviarEstado()
    { return super.enviarEstado().escribirAtributo("tipo","Guardian",ParteMensaje.FINAL); }
    @Override
    public void recibirEstado(String estado)
    { super.recibirEstado(estado); }
    
    
    @Override
    public void activarEspecial() 
    {
        if(enemigo.isActivado())
        { enemigo.pararEspecial(); }
        else
        {
            Gdx.app.log("ERROR","No hay un módulo activado que cancelar.");
            this.activado = false;
        }
        
        this.habilidad = new HabilidadGuardian(genesis,this);
    }
    @Override
    public void desactivarEspecial() 
    { this.habilidad = null; }
}
