package com.atarion.game.interfaz.escena;


import com.atarion.game.Atarion;
import com.atarion.game.entidad.jugador.humano.ClaseHumano;
import com.atarion.game.entidad.jugador.humano.DummyGeneric;
import com.atarion.game.entidad.jugador.humano.Humano;
import com.atarion.game.entidad.jugador.humano.cannon.Anarchist;
import com.atarion.game.entidad.jugador.humano.cannon.DummyCannon;
import com.atarion.game.entidad.jugador.humano.cannon.Fanatic;
import com.atarion.game.entidad.jugador.humano.cannon.Templar;
import com.atarion.game.entidad.jugador.humano.trench.Avenger;
import com.atarion.game.entidad.jugador.humano.trench.Benefactor;
import com.atarion.game.entidad.jugador.humano.trench.DummyTrench;
import com.atarion.game.entidad.jugador.humano.trench.Guardian;
import com.atarion.game.entidad.jugador.humano.wheel.DummyWheel;
import com.atarion.game.entidad.jugador.humano.wheel.Merchant;
import com.atarion.game.entidad.jugador.humano.wheel.Traveler;
import com.atarion.game.entidad.jugador.humano.wheel.Visionary;
import com.atarion.game.entidad.jugador.maquina.Maquina;
import com.atarion.game.interfaz.Interfaz;
import com.atarion.game.interfaz.menu.MenuDerrota;
import com.atarion.game.interfaz.menu.MenuVictoria;


public abstract class Escena extends Interfaz
{
    protected Humano humano = null, humano2 = null;
    protected Maquina maquina = null;
    protected boolean cmaquinahumano = false, chumano2humano = false, cmaquinahumano2 = false;
    protected boolean batalla;
    
    
    public void entrar(ClaseHumano clase)
    { 
        this.humano = this.asignarClase(humano,clase,true);
        
        if(this.humano2 != null)
        {
            this.humano.agregarEnemigo(this.humano2);
            this.humano2.agregarEnemigo(this.humano);
        }
        if(this.maquina != null)
        {
            humano.agregarEnemigo(maquina);
            maquina.agregarEnemigo(humano);
        }
        
        Atarion.getInstance().setScreen(this);
    }
    protected Humano asignarClase(Humano jugador,ClaseHumano clase,boolean tu)
    {
        switch(clase)
        {
            case ANARCHIST:
                jugador = new Anarchist(tu,this.batalla);
            break;
            case FANATIC:
                jugador = new Fanatic(tu,this.batalla);
            break;
            case TEMPLAR:
                jugador = new Templar(tu,this.batalla);
            break;
                
            case AVENGER:
                jugador = new Avenger(tu,this.batalla);
            break;
            case BENEFACTOR:
                jugador = new Benefactor(tu,this.batalla);
            break;
            case GUARDIAN:
                jugador = new Guardian(tu,this.batalla);
            break;
                
            case MERCHANT:
                jugador = new Merchant(tu,this.batalla);
            break;
            case TRAVELER:
                jugador = new Traveler(tu,this.batalla);
            break;
            case VISIONARY:
                jugador = new Visionary(tu,this.batalla);
            break;
            
            case DUMMYGENERIC:
                jugador = new DummyGeneric();
            break;
            case DUMMYCANNON:
                jugador = new DummyCannon();
            break;
            case DUMMYWHEEL:
                jugador = new DummyWheel();
            break;
            case DUMMYTRENCH:
                jugador = new DummyTrench();
            break;
        } 
        
        return jugador;
    }
    
    
    @Override
    public void show()
    { 
        super.show(); 

        humano.setGenesis(genesis);
        
        if(humano2 != null)
        { humano2.setGenesis(genesis); }
        if(maquina != null)
        { maquina.setGenesis(genesis); }
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
        { new MenuDerrota().mostrar(); }
        else if(maquina != null && maquina.getVida() <= 0)
        { new MenuVictoria().mostrar(); }
    }
    @Override
    public void dispose() 
    {
        humano.dispose();
        maquina.dispose();
    }
}
