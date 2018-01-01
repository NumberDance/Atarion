package com.atarion.game.entidad;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import org.json.JSONArray;


public abstract class Entidad extends Rectangle
{
    protected Batch genesis;
    protected Texture textura;
    
    
    public void actualizarEstado()
    { genesis.draw(textura,x,y); }
    public void dispose()
    { textura.dispose(); }
    
    
    protected abstract StringBuilder volcarEstado();
    protected abstract void recibirEstado(String estado);
    
    
    public void setGenesis(Batch genesis)
    { this.genesis = genesis; }
    public void setTextura(Texture textura)
    { this.textura = textura; }
}
