package com.atarion.game.interfaz.escena.tutorial;

import com.atarion.game.entidad.jugador.maquina.Dummy;
import com.atarion.game.interfaz.escena.Escena;

public class TutorialGuardian extends Escena
{
    public TutorialGuardian() 
    {
        super(Gdx.audio.newMusic(Gdx.files.internal("pirates.mp3")));
    }
    
    
    @Override
    public void show()
    {
        super.show();
        
        humano = new Guardian(genesis);
        maquina = new Dummy(genesis);
        
        humano.agregarEnemigo(maquina);
        maquina.agregarEnemigo(humano);
    }
}
