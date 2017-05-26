package com.atarion.game.entidad.habilidad;

import com.atarion.game.entidad.jugador.Jugador;
import com.badlogic.gdx.graphics.g2d.Batch;

public class HabilidadAnarchist extends Habilidad
{
    private Jugador enemigo;
    
    public HabilidadAnarchist(Batch genesis, Jugador jugador, Jugador enemigo)
    {
        super(genesis, jugador);
        this.enemigo = enemigo;
    }
}
