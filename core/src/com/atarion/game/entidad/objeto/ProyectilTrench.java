package com.atarion.game.entidad.objeto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class ProyectilTrench extends Proyectil
{
    public ProyectilTrench(Batch genesis, float x, float y, float direccionx, float direcciony)
    {
        super(genesis, x, y, direccionx, direcciony);
        
        this.textura = new Texture(Gdx.files.internal("proyectiletrench.png"));
        this.dureza = 5;
        this.velocidad *= 2;
        
        this.width = 50;
        this.height = 50;
    }
}
