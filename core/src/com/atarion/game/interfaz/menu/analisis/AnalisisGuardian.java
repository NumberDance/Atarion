package com.atarion.game.interfaz.menu.analisis;

import com.atarion.game.Atarion;
import com.atarion.game.interfaz.menu.MenuPrincipal;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

public class AnalisisGuardian extends Analisis
{
    public AnalisisGuardian()
    {
        super();
        analisis = new Texture(Gdx.files.internal("guardiananalysis.png"));
        
        tema = Gdx.audio.newMusic(Gdx.files.internal("pirates.mp3"));
        tema.setLooping(true);
    }

    
    @Override
    protected void controlarTeclado() 
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT))
        { Atarion.getInstance().setScreen(new MenuPrincipal()); }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT))
        { Atarion.getInstance().setScreen(new AnalisisTraveler()); }
    }
}
