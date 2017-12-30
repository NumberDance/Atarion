package com.atarion.game.interfaz.escena.online;

import com.atarion.game.entidad.jugador.humano.trench.Guardian;

public class EscenaPVP extends EscenaOnline
{   
    public EscenaPVP() 
    { super(null); }
    
    
    @Override
    public void show()
    {
        super.show();

        humano = new Guardian(genesis,true);
        humano.setX(800 / 2 - 64 / 2);
        humano.setY(60);

        humano2 = new Guardian(genesis,true);
        humano2.setX(800 / 2 - 64 / 2);
        humano2.setY(30);
            
        humano.agregarEnemigo(humano2);
        humano2.agregarEnemigo(humano);
    }
}