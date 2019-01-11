package com.atarion.game.entidad.objeto;


import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.badlogic.gdx.graphics.g2d.Batch;


public class ProyectilBrutus extends Proyectil
{
    public ProyectilBrutus(Batch genesis, float x, float y, float direccionx, float direcciony)
    { 
        super(genesis, x, y, direccionx, direcciony); 
        this.velocidad *= 4;
    }
    public ProyectilBrutus(Batch genesis, String estado)
    { super(genesis,estado); }

    
    @Override
    public MensajeJSON enviarEstado()
    { return super.enviarEstado().escribirAtributo(null,null); }
}
