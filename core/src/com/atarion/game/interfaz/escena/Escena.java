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
    protected Humano humano = null, oponente = null;
    protected Maquina maquina = null;
    protected boolean cmaquinahumano = false, coponentehumano = false, cmaquinaoponente = false;
    
    
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
        if(oponente != null)
        { oponente.actualizarEstado(); }
        if(maquina != null)
        { maquina.actualizarEstado(); }
        
        genesis.end();
      
        humano.jugar(camara);
        if(oponente != null)
        { oponente.jugar(camara); }
        if(maquina != null)
        { maquina.jugar(camara); }

        this.controlColisiones();
        this.controlResultado();
    }
    private void controlColisiones()
    {   
        if(maquina != null)
        {   
            if(maquina.overlaps(humano))
            {
                if(!cmaquinahumano)
                {
                    humano.colisionJugador(maquina);
                    maquina.colisionJugador(humano);
                
                    cmaquinahumano = true;
                }
            }       
            else
            { cmaquinahumano = false; }
        }
        
        if(oponente != null)
        {
            if(oponente.overlaps(humano))
            {
                if(!coponentehumano)
                {
                    humano.colisionJugador(oponente);
                    oponente.colisionJugador(humano);
                
                    coponentehumano = true;
                }
            }       
            else
            { this.coponentehumano = false; }
        }
        
        if(oponente != null && maquina != null)
        {
            if(oponente.overlaps(maquina))
            {
                if(!cmaquinaoponente)
                {
                    maquina.colisionJugador(oponente);
                    oponente.colisionJugador(maquina);
                
                    cmaquinaoponente = true;
                }
            }       
            else
            { this.cmaquinaoponente = false; }
        }
    }
    private void controlResultado()
    {
        if(humano.getVida() <= 0)
        { Atarion.getInstance().setScreen(new MenuDerrota()); }
        else if(maquina != null && maquina.getVida() <= 0)
        { Atarion.getInstance().setScreen(new MenuVictoria()); }
    }
    
    
    @Override
    public void dispose() 
    {
        humano.dispose();
        maquina.dispose();
    }
}
