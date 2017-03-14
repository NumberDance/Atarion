package com.atarion.game.escena.analisis;

import com.atarion.game.escena.analisis.Analisis;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class AnalisisCrasus extends Analisis
{
    public AnalisisCrasus()
    {
        super();
        analisis = new Texture(Gdx.files.internal("crasusanalysis.png"));
    }    
}
