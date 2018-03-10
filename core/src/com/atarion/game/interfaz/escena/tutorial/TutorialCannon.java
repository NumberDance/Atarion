package com.atarion.game.interfaz.escena.tutorial;

import com.atarion.game.Atarion;
import com.atarion.game.entidad.jugador.humano.ClaseHumano;
import com.atarion.game.entidad.jugador.humano.cannon.DummyCannon;
import com.atarion.game.entidad.jugador.maquina.Dummy;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

public class TutorialCannon extends Tutorial
{
    public TutorialCannon()
    {
        super();
        
        this.insignia = new Texture(Gdx.files.internal("cannoninsignia.png"));
        this.textotitulo = "Cannon";
        this.textodescripcion = "Clase con grandes daños pero de escasa resistencia. No reciben daño de colisión de jugadores.\n";
        this.textodescripcion += "Hacen rebotar en la dirección opuesta, lo que permite proteger posiciones estratégicas.";
        
        this.titulo.setColor(255,0,0,255);
    }
    
    
    @Override
    public void show()
    {
        super.show();
        
        humano = new DummyCannon(genesis,true);
        maquina = new Dummy(genesis);
        
        humano.agregarEnemigo(maquina);
        maquina.agregarEnemigo(humano);
    }

    
    @Override
    public void controlarTeclado()
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) 
        { new TutorialWheel().entrar(ClaseHumano.DUMMYWHEEL); }
    }
}
