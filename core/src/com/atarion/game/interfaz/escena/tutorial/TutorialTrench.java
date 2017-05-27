package com.atarion.game.interfaz.escena.tutorial;

import com.atarion.game.Atarion;
import com.atarion.game.entidad.jugador.humano.trench.DummyTrench;
import com.atarion.game.entidad.jugador.maquina.Dummy;
import com.atarion.game.interfaz.menu.MenuPrincipal;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

public class TutorialTrench extends Tutorial
{
    public TutorialTrench() 
    {
        super();
        
        this.insignia = new Texture(Gdx.files.internal("trenchinsignia.png"));
        this.textotitulo = "Trench";
        this.textodescripcion = "Clase con gran resistencia pero de escasa velocidad. Capaz de atravesar objetos.\n";
        this.textodescripcion += "Rompen obstáculos para crear rutas de escape para sus compañeros de equipo.";
        this.textosiguiente = "PULSA  ENTER  PARA  SALTAR  AL  MENU  PRINCIPAL";
        
        this.titulo.setColor(0,255,0,255);
        this.descripcion.setColor(0,255,0,255);
    }
    
    
    @Override
    public void show()
    {
        super.show();
        
        humano = new DummyTrench(genesis);
        maquina = new Dummy(genesis);
        
        humano.agregarEnemigo(maquina);
        maquina.agregarEnemigo(humano);
    }

    
    @Override
    public void controlarTeclado()
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) 
        { Atarion.getInstance().setScreen(new MenuPrincipal()); }
    }
}
