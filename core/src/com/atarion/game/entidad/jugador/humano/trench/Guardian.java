package com.atarion.game.entidad.jugador.humano.trench;

import com.atarion.game.entidad.jugador.humano.trench.Trench;
import com.atarion.game.entidad.habilidad.HabilidadGuardian;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Guardian extends Trench
{
    public Guardian(Batch genesis) 
    {
        super(genesis);
        this.textura = new Texture(Gdx.files.internal("guardian.png"));
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
}
