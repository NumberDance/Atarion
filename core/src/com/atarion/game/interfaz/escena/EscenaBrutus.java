package com.atarion.game.interfaz.escena;


import com.atarion.game.entidad.jugador.humano.ClaseHumano;
import com.atarion.game.entidad.jugador.maquina.Brutus;


public class EscenaBrutus extends Escena
{
    public EscenaBrutus() 
    { 
        super();
        this.batalla = true;
    }
    
    
    @Override
    public void entrar(ClaseHumano clase)
    {
        this.maquina = new Brutus(this.batalla);
        super.entrar(clase);
    }
}
