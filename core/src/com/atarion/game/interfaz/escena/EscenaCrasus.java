package com.atarion.game.interfaz.escena;


import com.atarion.game.entidad.jugador.humano.ClaseHumano;
import com.atarion.game.entidad.jugador.maquina.Crasus;


public class EscenaCrasus extends Escena
{   
    public EscenaCrasus() 
    { 
        super();
        //super.musica("zombies.mp3"); 
    }
    
    
    @Override
    public void entrar(ClaseHumano clase)
    {
        this.maquina = new Crasus();
        super.entrar(clase);
    }
}
