package com.atarion.game.escena;

import com.atarion.game.entidad.jugador.humano.Traveler;
import com.atarion.game.entidad.jugador.maquina.Brutus;

public class EscenaBrutus extends Escena
{
    @Override
    public void show()
    {
        super.show();
        
        humano = new Traveler(genesis);
        maquina = new Brutus(genesis);
        
        humano.agregarEnemigo(maquina);
        maquina.agregarEnemigo(humano);
    }
}
