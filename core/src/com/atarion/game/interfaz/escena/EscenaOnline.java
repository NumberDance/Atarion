package com.atarion.game.interfaz.escena;

import com.atarion.game.red.Servidor;
import com.badlogic.gdx.audio.Music;

public class EscenaOnline extends Escena
{
    private Servidor servidor = new Servidor(this);
    
    public EscenaOnline(Music tema)
    {
        super(tema);
    }

    
}
