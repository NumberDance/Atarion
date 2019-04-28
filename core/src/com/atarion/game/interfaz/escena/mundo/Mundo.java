package com.atarion.game.interfaz.escena.mundo;

import com.atarion.game.entidad.jugador.humano.cannon.Templar;
import com.atarion.game.interfaz.escena.Escena;

public class Mundo extends Escena
{
    public Mundo()
    {
        this.humano = new Templar(true,false);
        this.batalla = false;
    }
}
