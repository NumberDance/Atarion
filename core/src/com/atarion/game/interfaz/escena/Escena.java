package com.atarion.game.interfaz.escena;

import com.atarion.game.Atarion;
import com.atarion.game.entidad.jugador.humano.Humano;
import com.atarion.game.entidad.jugador.humano.cannon.Anarchist;
import com.atarion.game.entidad.jugador.humano.cannon.Fanatic;
import com.atarion.game.entidad.jugador.humano.cannon.Templar;
import com.atarion.game.entidad.jugador.humano.trench.Avenger;
import com.atarion.game.entidad.jugador.humano.trench.Benefactor;
import com.atarion.game.entidad.jugador.humano.trench.Guardian;
import com.atarion.game.entidad.jugador.humano.wheel.Merchant;
import com.atarion.game.entidad.jugador.humano.wheel.Traveler;
import com.atarion.game.entidad.jugador.humano.wheel.Visionary;
import com.atarion.game.entidad.jugador.maquina.Maquina;
import com.atarion.game.interfaz.Interfaz;
import com.atarion.game.interfaz.escena.online.servidor.Clase;
import com.atarion.game.interfaz.menu.MenuDerrota;
import com.atarion.game.interfaz.menu.MenuVictoria;
import com.badlogic.gdx.audio.Music;

public abstract class Escena extends Interfaz
{
    protected Humano humano = null, humano2 = null;
    protected Maquina maquina = null;
    protected boolean cmaquinahumano = false, chumano2humano = false, cmaquinahumano2 = false;
    protected Clase clase = null;
    
    
    protected Escena(Music tema)
    {
        this.tema = tema;
        
        if(tema != null)
        { tema.setLooping(true); }
    }
    public void entrar(Clase clase)
    {
        this.clase = clase;
        Atarion.getInstance().setScreen(this);
    }
    
    
    @Override
    public void show()
    {
        super.show();
        
        switch(this.clase)
        {
            case ANARCHIST:
                humano = new Anarchist(this.genesis,true);
            break;
            case FANATIC:
                humano = new Fanatic(this.genesis,true);
            break;
            case TEMPLAR:
                humano = new Templar(this.genesis,true);
            break;
                
            case AVENGER:
                humano = new Avenger(this.genesis,true);
            break;
            case BENEFACTOR:
                humano = new Benefactor(this.genesis,true);
            break;
            case GUARDIAN:
                humano = new Guardian(this.genesis,true);
            break;
                
            case MERCHANT:
                humano = new Merchant(this.genesis,true);
            break;
            case TRAVELER:
                humano = new Traveler(this.genesis,true);
            break;
            case VISIONARY:
                humano = new Visionary(this.genesis,true);
            break;
        } 
    }
    @Override
    public void render(float delta) 
    {
        super.render(delta);
      
        genesis.begin();
        
        humano.actualizarEstado();
        if(humano2 != null)
        { humano2.actualizarEstado(); }
        if(maquina != null)
        { maquina.actualizarEstado(); }
        
        genesis.end();
      
        humano.jugar(camara);
        if(humano2 != null)
        { humano2.jugar(camara); }
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
        
        if(humano2 != null)
        {
            if(humano2.overlaps(humano))
            {
                if(!chumano2humano)
                {
                    humano.colisionJugador(humano2);
                    humano2.colisionJugador(humano);
                
                    chumano2humano = true;
                }
            }       
            else
            { this.chumano2humano = false; }
        }
        
        if(humano2 != null && maquina != null)
        {
            if(humano2.overlaps(maquina))
            {
                if(!cmaquinahumano2)
                {
                    maquina.colisionJugador(humano2);
                    humano2.colisionJugador(maquina);
                
                    cmaquinahumano2 = true;
                }
            }       
            else
            { this.cmaquinahumano2 = false; }
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
