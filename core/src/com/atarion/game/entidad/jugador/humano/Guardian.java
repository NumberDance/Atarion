package com.atarion.game.entidad.jugador.humano;

import com.atarion.game.entidad.habilidad.HabilidadGuardian;
import com.atarion.game.entidad.objeto.Objeto;
import com.atarion.game.entidad.jugador.Jugador;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Guardian extends Humano
{
    private HabilidadGuardian habilidad = null;
    private boolean inmune;
    
    
    public Guardian(Batch genesis) 
    {
        super(genesis);
        
        this.textura = new Texture(Gdx.files.internal("guardian.png"));
        this.vida = 200;
        this.inmune = true;
    }
    
    
    @Override
    public void actualizarEstado()
    {
       super.actualizarEstado();
        
       if(habilidad != null)
       { habilidad.actualizarEstado(); }
    }
    
    
    @Override
    public void agregarEnemigo(Jugador jugador) 
    { enemigo = jugador; }
    
    
    @Override
    public void colisionObjeto(Objeto objeto)
    {
        if(this.inmune)
        { Gdx.app.log("INFO","Las barreras de la clase Guardian pueden atravesar objetos."); }
        else
        {
            Gdx.app.log("INFO","De repente, te hiciste vulnerable a los objetos. 100 puntos de daño y te quedan " + this.getVida() + "vidas");
            this.vida -= 100;
        }
    }
    
    
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

    
    public void setInmune(boolean inmune) 
    { this.inmune = inmune; }
}
