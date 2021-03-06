package com.atarion.game.entidad.jugador.humano.trench;


import com.atarion.game.entidad.habilidad.HabilidadGuardian;
import com.atarion.game.entidad.jugador.humano.ClaseHumano;
import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class Guardian extends Trench
{
    public Guardian(boolean tu,boolean batalla)
    {
        super(tu,batalla);
        
        this.clase = ClaseHumano.GUARDIAN;
        this.textura = new Texture(Gdx.files.internal("textures/guardian.png"));
    }
    
    
    @Override
    public MensajeJSON enviarEstado()
    { return super.enviarEstado().escribirAtributo("tipo","Guardian"); }
    @Override
    public void recibirEstado(String estado)
    { super.recibirEstado(estado); }
    
    
    @Override
    public void activarEspecial() 
    {
        if(enemigos.get(0).isActivado())
        { 
            enemigos.get(0).pararEspecial(); 
            enemigos.get(0).setInteraccion(true);
        }
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
