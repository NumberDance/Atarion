package com.atarion.game.interfaz.escena;


import com.atarion.game.entidad.jugador.humano.ClaseHumano;
import com.atarion.game.entidad.jugador.maquina.Crasus;


public class EscenaCrasus extends Escena
{   
    public EscenaCrasus() 
    { 
        super();
        this.batalla = true;
    }
    
    
    @Override
    public void entrar(ClaseHumano clase)
    {
        this.maquina = new Crasus(this.batalla);
        super.entrar(clase);
    }
}
