package com.atarion.game.entidad.habilidad;

import com.atarion.game.entidad.jugador.Jugador;

public class HabilidadFanatic extends Habilidad
{
    public HabilidadFanatic(Batch genesis, Jugador jugador)
    {
        super(genesis, jugador);
        this.textura = new Texture(Gdx.files.internal("bomboff.png"));
        
        jugador.setFuerza(150);
    }
}
