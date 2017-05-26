package com.atarion.game.interfaz.escena.tutorial;

import com.atarion.game.entidad.jugador.humano.wheel.Traveler;
import com.atarion.game.entidad.jugador.maquina.Dummy;
import com.atarion.game.interfaz.escena.Escena;

public class TutorialTraveler extends Escena
{
    public TutorialTraveler() 
    {
        super(Gdx.audio.newMusic(Gdx.files.internal("pirates.mp3")));
    }
    
    
    @Override
    public void show()
    {
        super.show();
        
        humano = new Traveler(genesis);
        maquina = new Dummy(genesis);
        
        humano.agregarEnemigo(maquina);
        maquina.agregarEnemigo(humano);
    }
}
