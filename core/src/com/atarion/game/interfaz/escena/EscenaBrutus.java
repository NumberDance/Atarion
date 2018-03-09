package com.atarion.game.interfaz.escena;

import com.atarion.game.entidad.jugador.humano.wheel.Traveler;
import com.atarion.game.entidad.jugador.maquina.Brutus;
import com.badlogic.gdx.Gdx;

public class EscenaBrutus extends Escena
{
    public EscenaBrutus() 
    {
        super(Gdx.audio.newMusic(Gdx.files.internal("commodus.mp3")));
    }
    
    
    @Override
    public void show()
    {
        super.show();
        maquina = new Brutus(genesis);
        
        humano.agregarEnemigo(maquina);
        maquina.agregarEnemigo(humano);
    }
}
