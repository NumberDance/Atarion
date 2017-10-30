package com.atarion.game.interfaz.escena.online;

import com.atarion.game.entidad.jugador.humano.trench.Guardian;
import com.badlogic.gdx.Gdx;
import java.net.ServerSocket;

public class EscenaPVP extends EscenaOnline
{   
    public EscenaPVP() 
    { super(Gdx.audio.newMusic(Gdx.files.internal("commodus.mp3"))); }
    
    
    @Override
    public void show()
    {
        super.show();
        
        if(humano != null)
        {
            humano = new Guardian(genesis,true);
            humano.setX(800 / 2 - 64 / 2);
            humano.setY(60);
        }
        else
        {
            humano2 = new Guardian(genesis,true);
            humano2.setX(800 / 2 - 64 / 2);
            humano2.setY(30);
            
            humano.agregarEnemigo(humano2);
            humano2.agregarEnemigo(humano);
        }
    }
}