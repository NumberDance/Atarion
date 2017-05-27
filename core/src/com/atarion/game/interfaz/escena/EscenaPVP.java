package com.atarion.game.interfaz.escena;

import com.atarion.game.Atarion;
import com.atarion.game.entidad.jugador.humano.Humano;
import com.atarion.game.entidad.jugador.humano.cannon.Templar;
import com.atarion.game.entidad.jugador.humano.trench.Guardian;
import com.atarion.game.entidad.jugador.maquina.Crasus;
import com.atarion.game.interfaz.menu.MenuDerrota;
import com.atarion.game.interfaz.menu.MenuVictoria;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class EscenaPVP extends Escena
{
    private Humano oponente;
    private boolean colision1 = false, colision2 = false, colision3 = false;
    
    public EscenaPVP()
    { 
        super(Gdx.audio.newMusic(Gdx.files.internal("finale.mp3")));
        
        this.humano = new Templar(genesis);
        this.oponente = new Guardian(genesis);
        this.maquina = new Crasus(genesis);
        
        humano.agregarEnemigo(oponente);
        humano.agregarEnemigo(maquina);
        
        oponente.agregarEnemigo(humano);
        oponente.agregarEnemigo(maquina);
    }
    
    @Override
    public void render(float delta) 
    { 
        super.render(delta);
        
        genesis.begin();
        oponente.actualizarEstado();
        genesis.end();
      
        humano.jugar(camara);
        oponente.jugar(camara);
        maquina.jugar(camara);
      
        if(humano.overlaps(oponente))
        {
            if(!colision)
            {
                humano.colisionJugador(oponente);
                oponente.colisionJugador(humano);
                colision1 = true;
            }
        }
        else
        { colision1 = false; }
        
        if(humano.overlaps(maquina))
        {
            if(!colision)
            {
                humano.colisionJugador(maquina);
                maquina.colisionJugador(humano);
                colision2 = true;
            }
        }
        else
        { colision2 = false; }
        
        if(oponente.overlaps(maquina))
        {
            if(!colision)
            {
                oponente.colisionJugador(maquina);
                maquina.colisionJugador(oponente);
                colision3 = true;
            }
        }
        else
        { colision3 = false; }
      
        if(humano.getVida() <= 0)
        { Atarion.getInstance().setScreen(new MenuDerrota()); }
        else if(oponente.getVida() <= 0)
        { Atarion.getInstance().setScreen(new MenuVictoria()); }
    }
}