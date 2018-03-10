package com.atarion.game.interfaz.escena.tutorial;

import com.atarion.game.Atarion;
import com.atarion.game.entidad.jugador.humano.ClaseHumano;
import com.atarion.game.entidad.jugador.humano.wheel.DummyWheel;
import com.atarion.game.entidad.jugador.maquina.Dummy;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

public class TutorialWheel extends Tutorial
{
    public TutorialWheel() 
    {
        super();
        
        this.insignia = new Texture(Gdx.files.internal("wheelinsignia.png"));
        this.textotitulo = "Wheel";
        this.textodescripcion = "Clase con gran velocidad pero de escasa fuerza. Pueden teletransportarse de un borde a otro.\n";
        this.textodescripcion += "Su superior velocidad y esquiva les permite desviar la atención del enemigo de compañeros vulnerables.";
        
        this.titulo.setColor(0,0,255,255);
    }
    
    
    @Override
    public void show()
    {
        super.show();
        
        humano = new DummyWheel(genesis,true);
        maquina = new Dummy(genesis);
        
        humano.agregarEnemigo(maquina);
        maquina.agregarEnemigo(humano);
    }

    
    @Override
    public void controlarTeclado()
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) 
        { new TutorialTrench().entrar(ClaseHumano.DUMMYTRENCH); }
    }
}
