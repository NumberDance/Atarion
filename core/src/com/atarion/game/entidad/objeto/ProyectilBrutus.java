package com.atarion.game.entidad.objeto;

import com.badlogic.gdx.graphics.g2d.Batch;

public class ProyectilBrutus extends Proyectil
{
    public ProyectilBrutus(Batch genesis, float x, float y, float direccionx, float direcciony)
    { super(genesis, x, y, direccionx, direcciony); }
    public ProyectilBrutus(Batch genesis, String estado)
    { super(genesis,estado); }

    
    @Override
    public StringBuilder volcarEstado()
    { return super.volcarEstado().append("}"); }
}
