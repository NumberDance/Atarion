package com.atarion.game.interfaz.menu.analisis;

import com.atarion.game.Atarion;
import com.atarion.game.interfaz.escena.EscenaBrutus;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

public class AnalisisBrutus extends Analisis
{
    public AnalisisBrutus()
    {
        super();
        analisis = new Texture(Gdx.files.internal("brutusanalysis.png"));
    }
    
    
    @Override
    protected void controlarTeclado() 
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY))
        { Atarion.getInstance().setScreen(new EscenaBrutus()); }
    }
}
