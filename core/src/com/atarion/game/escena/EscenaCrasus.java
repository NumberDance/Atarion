package com.atarion.game.escena;

import com.atarion.game.entidad.jugador.humano.Templar;
import com.atarion.game.entidad.jugador.maquina.Crasus;

public class EscenaCrasus extends Escena
{
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
