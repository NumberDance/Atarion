package com.atarion.game.interfaz.escena;

import com.atarion.game.entidad.jugador.humano.Templar;
import com.atarion.game.entidad.jugador.maquina.Crasus;
import com.badlogic.gdx.Gdx;

public class EscenaCrasus extends Escena
{   
    public EscenaCrasus() 
    { super(Gdx.audio.newMusic(Gdx.files.internal("zombies.mp3"))); }
    
    
    @Override
    public void show()
    {
        super.show();
        
        humano = new Templar(genesis);
        maquina = new Crasus(genesis);
        
        humano.agregarEnemigo(maquina);
        maquina.agregarEnemigo(humano);
    }   
}
