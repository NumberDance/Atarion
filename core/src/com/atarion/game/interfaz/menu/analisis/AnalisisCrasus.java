package com.atarion.game.interfaz.menu.analisis;

import com.atarion.game.Atarion;
import com.atarion.game.interfaz.escena.EscenaCrasus;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

public class AnalisisCrasus extends Analisis
{
    public AnalisisCrasus()
    {
        super();
        analisis = new Texture(Gdx.files.internal("crasusanalysis.png"));
    }    
    
    
    @Override
    protected void controlarTeclado() 
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY))
        { Atarion.getInstance().setScreen(new EscenaCrasus()); }
    }
}
