package com.atarion.game.interfaz.escena;


import com.atarion.game.entidad.jugador.maquina.Crasus;


public class EscenaCrasus extends Escena
{   
    public EscenaCrasus() 
    { 
        super();
        super.musica("zombies.mp3"); 
    }
    
    
    @Override
    public void show()
    {
        super.show();
        maquina = new Crasus(genesis);
        
        humano.agregarEnemigo(maquina);
        maquina.agregarEnemigo(humano);
    }   
}
