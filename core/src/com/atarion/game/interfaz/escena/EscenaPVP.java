package com.atarion.game.interfaz.escena;

import com.atarion.game.entidad.jugador.humano.trench.Guardian;
import com.atarion.game.entidad.jugador.humano.wheel.Traveler;
import com.badlogic.gdx.Gdx;

public class EscenaPVP extends Escena
{   
    public EscenaPVP() 
    {
        super(Gdx.audio.newMusic(Gdx.files.internal("commodus.mp3")));
    }
    
    
    @Override
    public void show()
    {
        super.show();
        
        humano = new Traveler(genesis,true);
        humano.setX(800 / 2 - 64 / 2);
        humano.setY(60);
        
        oponente = new Guardian(genesis,false);
        oponente.setX(800 / 2 - 64 / 2);
        oponente.setY(30);
        
        humano.agregarEnemigo(oponente);
        oponente.agregarEnemigo(humano);
    }
}