package com.atarion.game.interfaz.escena.mundo;

import com.atarion.game.entidad.jugador.humano.cannon.Templar;
import com.atarion.game.interfaz.escena.Escena;

public class Mundo extends Escena {

    public Mundo() {
        this.humanosAliados.add(new Templar(false,false));
        this.batalla = false;
        
        this.cameraWidth -= 200;
        this.cameraHeight -= 200;
    }
}
