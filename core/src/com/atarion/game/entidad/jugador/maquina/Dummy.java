package com.atarion.game.entidad.jugador.maquina;


import com.atarion.game.entidad.jugador.Jugador;
import com.atarion.game.interfaz.escena.Escena;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class Dummy extends Maquina
{
    public Dummy(boolean batalla) 
    {
        super(batalla);
        this.y = 400;
        
        this.textura = new Texture(Gdx.files.internal("textures/dummy.png"));
        this.vida = 999999999;
        this.fuerza = 0;
    }
    
    
    @Override
    public void recibirEstado(String estado)
    { super.recibirEstado(estado); }
    

    @Override
    protected void controlBordes(Escena escena)
    {
        if(this.y < 0) 
        { this.y = escena.getTotalHeight() - 150; } 
        else if(this.y > escena.getTotalHeight() - 100)
        { this.y = 0 + 150; }
        else if(this.x < 0)
        { this.x = escena.getTotalWidth() - 150; }
        else if(this.x > escena.getTotalWidth() - 100) 
        { this.x = 0 + 150; }
    }

    @Override
    public void agregarEnemigo(Jugador jugador)
    {}

    @Override
    public void activarEspecial()
    {}
    @Override
    public void desactivarEspecial()
    {}
    
    
    @Override
    protected void faseDos()
    {}
    @Override
    protected void faseTres()
    {}
    @Override
    protected void faseCuatro()
    {}
    @Override
    protected void faseCinco()
    {}
    @Override
    protected void faseSeis()
    {}
    @Override
    protected void faseSiete()
    {}
    @Override
    protected void faseOcho()
    {}
    @Override
    protected void faseNueve()
    {}
    @Override
    protected void faseDiez()
    {}
}
