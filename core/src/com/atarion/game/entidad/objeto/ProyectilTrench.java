package com.atarion.game.entidad.objeto;

import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.atarion.game.interfaz.escena.online.ParteMensaje;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class ProyectilTrench extends Proyectil
{
    public ProyectilTrench(Batch genesis, float x, float y, float direccionx, float direcciony)
    {
        super(genesis, x, y, direccionx, direcciony);
        this.parametrosIniciales();
    }
    public ProyectilTrench(Batch genesis, String estado)
    { 
        super(genesis,estado); 
        this.parametrosIniciales();
    }
    private void parametrosIniciales()
    {
        this.textura = new Texture(Gdx.files.internal("proyectiletrench.png"));
        this.dureza = 5;
        this.velocidad *= 2;
        
        this.width = 50;
        this.height = 50;
    }
    
    
    @Override
    public MensajeJSON enviarEstado()
    { return super.enviarEstado().escribirAtributo(null,null,ParteMensaje.FINAL); }
}
