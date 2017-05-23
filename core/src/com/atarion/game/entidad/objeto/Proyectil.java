package com.atarion.game.entidad.objeto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Proyectil extends Objeto
{
    protected float posicionx, posiciony;
    protected float direccionx, direcciony;
    
    protected boolean destino = false;
    protected float velocidad = 1f;
    
    
    public Proyectil(Batch genesis, float posicionx, float posiciony, float direccionx, float direcciony)
    {
        super(genesis,new Texture(Gdx.files.internal("proyectile.png")),10);
        
        this.width = 100;
        this.height = 100;
        
        this.posicionx = posicionx;
        this.posiciony = posiciony;
        this.direccionx = direccionx;
        this.direcciony = direcciony;
    }
    
    
    public void lanzar()
    {
        if(this.x > 1000 + 100 || this.x < 0 - 100 || this.y > 800 + 100 || this.y < 0 - 100)
        { this.dispose(); }
        else if(this.x == direccionx && this.y == direcciony)
        { destino = true; }
        else
        {
            if(this.x < direccionx)
            { this.x += 200 * Gdx.graphics.getDeltaTime() * this.velocidad; }
            else if(this.x > direccionx)
            { this.x -= 200 * Gdx.graphics.getDeltaTime() * this.velocidad; }
            
            if(this.y < direcciony)
            { this.y += 200 * Gdx.graphics.getDeltaTime() * this.velocidad; }
            else if(this.y > direcciony)
            { this.y -= 200 * Gdx.graphics.getDeltaTime() * this.velocidad; }
        }
    }
    public boolean isDestino() 
    { return destino; }
}
