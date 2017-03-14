package com.atarion.game.escena.analisis;

import com.atarion.game.escena.analisis.Analisis;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class AnalisisClaudius extends Analisis
{
    public AnalisisClaudius()
    {
        super();
        analisis = new Texture(Gdx.files.internal("claudiusanalysis.png"));
    }    
}
