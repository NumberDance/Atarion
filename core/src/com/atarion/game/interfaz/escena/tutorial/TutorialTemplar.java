package com.atarion.game.interfaz.escena.tutorial;

import com.atarion.game.entidad.jugador.humano.cannon.Templar;
import com.atarion.game.entidad.jugador.maquina.Dummy;
import com.atarion.game.interfaz.escena.Escena;

public class TutorialTemplar extends Escena
{
    public TutorialTemplar() 
    {
        super(Gdx.audio.newMusic(Gdx.files.internal("pirates.mp3")));
    }
    
    
    @Override
    public void show()
    {
        super.show();
        
        humano = new Templar(genesis);
        maquina = new Dummy(genesis);
        
        humano.agregarEnemigo(maquina);
        maquina.agregarEnemigo(humano);
    }
}
