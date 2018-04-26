package com.atarion.game.interfaz.escena;


import com.atarion.game.entidad.jugador.humano.ClaseHumano;
import com.atarion.game.entidad.jugador.maquina.Brutus;


public class EscenaBrutus extends Escena
{
    public EscenaBrutus() 
    { 
        super();
        //super.musica("commodus.mp3"); 
    }
    
    
    @Override
    public void entrar(ClaseHumano clase)
    {
        this.maquina = new Brutus();
        super.entrar(clase);
    }
}
