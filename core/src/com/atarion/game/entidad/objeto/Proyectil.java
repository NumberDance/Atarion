package com.atarion.game.entidad.objeto;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;
import org.json.JSONTokener;


@Getter
@Setter
public abstract class Proyectil extends Objeto
{
    protected float direccionx, direcciony;
    protected boolean destino = false;
    protected float velocidad = 1f;
    
    
    public Proyectil(Batch genesis, float x, float y, float direccionx, float direcciony)
    {
        super(genesis,new Texture(Gdx.files.internal("proyectile.png")),10);
        
        this.width = 100;
        this.height = 100;
        
        this.x = x;
        this.y = y;
        this.direccionx = direccionx;
        this.direcciony = direcciony;
    }
    public Proyectil(Batch genesis, String estado)
    {
        super(genesis,new Texture(Gdx.files.internal("proyectile.png")),10);
        
        JSONObject objeto = new JSONObject(new JSONTokener(estado));
        this.x = objeto.getFloat("x");
        this.y = objeto.getFloat("y");
        this.direccionx = objeto.getFloat("direccionx");
        this.direcciony = objeto.getFloat("direcciony");
        this.destino = objeto.getBoolean("destino");
        this.velocidad = objeto.getFloat("velocidad");
    }
    
    
    @Override
    public void recibirEstado(String estado)
    { 
        super.recibirEstado(estado); 
        
        JSONObject objeto = new JSONObject(new JSONTokener(estado));
        this.direccionx = objeto.getFloat("direccionx");
        this.direcciony = objeto.getFloat("direcciony");
        this.destino = objeto.getBoolean("destino");
        this.velocidad = objeto.getFloat("velocidad");
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
}
