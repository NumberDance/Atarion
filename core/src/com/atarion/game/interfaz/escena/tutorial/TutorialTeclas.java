package com.atarion.game.interfaz.escena.tutorial;

import com.atarion.game.Atarion;
import com.atarion.game.entidad.jugador.humano.ClaseHumano;
import com.atarion.game.entidad.jugador.humano.DummyGeneric;
import com.atarion.game.entidad.jugador.maquina.Dummy;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

public class TutorialTeclas extends Tutorial
{
    public TutorialTeclas()
    {
        super();
        
        this.textotitulo = "Teclas";
        this.textodescripcion = "Usa las teclas UP,DOWN,LEFT,RIGHT para mover la barrera.\n";
        this.textodescripcion += "Pulsa ESPACIO para activar la habilidad especial.";
        this.textosiguiente = "PULSA  ENTER  PARA  VER  LOS  TIPOS  DE  BARRA";
    }
    
    
    @Override
    public void show()
    {
        super.show();
        humano = new DummyGeneric(genesis,true);
        maquina = new Dummy(genesis);
        
        humano.agregarEnemigo(maquina);
        maquina.agregarEnemigo(humano);
    }
    
    
    @Override
    public void controlarTeclado()
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) 
        { new TutorialCannon().entrar(ClaseHumano.DUMMYCANNON); }
    }
}
