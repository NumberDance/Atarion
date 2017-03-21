package com.atarion.game.entidad.objeto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Bomba extends Objeto
{
    private int cuenta = 3;
    
    public Bomba(Batch genesis, float x, float y) 
    {
        super(genesis,new Texture(Gdx.files.internal("bomb3.png")),10);
        
        this.dureza = 0;
        
        this.x = x;
        this.y = y;
        
        this.width = 100;
        this.height = 100;
    }
    
    public void cuentaAtras()
    {
        cuenta--;
        
        switch(cuenta)
        {
            case 3:
                this.textura = new Texture(Gdx.files.internal("bomb3.png"));
            break;
            case 2:
                this.textura = new Texture(Gdx.files.internal("bomb2.png"));
            break;
            case 1:
                this.textura = new Texture(Gdx.files.internal("bomb1.png"));
            break;
            case 0:
                this.x -= 200;
                this.y -= 200;
                
                this.width += 300;
                this.height += 300;
                
                this.dureza = 100;        
                this.textura = new Texture(Gdx.files.internal("bomboff.png"));
            break;
        }
    }
}
