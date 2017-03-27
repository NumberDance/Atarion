package com.atarion.game.interfaz.menu.analisis;

import com.atarion.game.Atarion;
import com.atarion.game.interfaz.escena.EscenaClaudius;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

public class AnalisisClaudius extends Analisis
{
    public AnalisisClaudius()
    {
        super();
        analisis = new Texture(Gdx.files.internal("claudiusanalysis.png"));
    }    
    
    @Override
    protected void controlarTeclado() 
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY))
        {
            Atarion.getInstance().setScreen(new EscenaClaudius());
        }
    }
}
