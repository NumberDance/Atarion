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
        this.humano = this.asignarClase(humano,clase,true);
        this.maquina = new Brutus();
        
        super.entrar(clase);
    }
}
