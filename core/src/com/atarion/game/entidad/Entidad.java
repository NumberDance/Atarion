package com.atarion.game.entidad;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entidad extends Rectangle
{
    protected Batch genesis;
    protected Texture textura;
    
    
    public void actualizarEstado()
    { genesis.draw(textura,x,y); }
    public void dispose()
    { textura.dispose(); }
}
