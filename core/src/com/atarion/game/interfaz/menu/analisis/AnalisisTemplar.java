package com.atarion.game.interfaz.menu.analisis;

import com.atarion.game.Atarion;
import com.atarion.game.interfaz.menu.MenuTeclas;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

public class AnalisisTemplar extends Analisis
{
    public AnalisisTemplar()
    {
        super();
        analisis = new Texture(Gdx.files.internal("templaranalysis.png"));
        
        tema = Gdx.audio.newMusic(Gdx.files.internal("pirates.mp3"));
        tema.setLooping(true);
    }
    
    
    @Override
    protected void controlarTeclado() 
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT))
        { Atarion.getInstance().setScreen(new AnalisisTraveler()); }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT))
        { Atarion.getInstance().setScreen(new MenuTeclas()); }
    }
}