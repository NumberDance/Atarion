package com.atarion.game.interfaz.escena;

import com.atarion.game.Atarion;
import com.atarion.game.entidad.jugador.humano.Humano;
import com.atarion.game.entidad.jugador.maquina.Maquina;
import com.atarion.game.interfaz.Interfaz;
import com.atarion.game.interfaz.menu.MenuDerrota;
import com.atarion.game.interfaz.menu.MenuVictoria;
import com.badlogic.gdx.audio.Music;

public abstract class Escena extends Interfaz
{
    protected Humano humano = null;
    protected Maquina maquina = null;
    protected boolean colision = false;
    
    
    protected Escena(Music tema)
    {
        this.tema = tema;
        tema.setLooping(true);
    }
    
    
    @Override
    public void render(float delta) 
    {
        super.render(delta);
      
        genesis.begin();
        humano.actualizarEstado();
        maquina.actualizarEstado();
        genesis.end();
      
        humano.jugar(camara);
        maquina.jugar(camara);
      
        if(maquina.overlaps(humano))
        {
            if(!colision)
            {
                humano.colisionJugador(maquina);
                maquina.colisionJugador(humano);
                colision = true;
            }
        } 
        else
        { colision = false; }
      
        if(humano.getVida() <= 0)
        { Atarion.getInstance().setScreen(new MenuDerrota()); }
        else if(maquina.getVida() <= 0)
        { Atarion.getInstance().setScreen(new MenuVictoria()); }
    }
    
    
    @Override
    public void dispose() 
    {
        humano.dispose();
        maquina.dispose();
    }
}
