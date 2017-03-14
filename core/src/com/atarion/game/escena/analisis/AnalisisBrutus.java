package com.atarion.game.escena.analisis;

import com.atarion.game.escena.analisis.Analisis;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class AnalisisBrutus extends Analisis
{
    public AnalisisBrutus()
    {
        super();
        analisis = new Texture(Gdx.files.internal("brutusanalysis.png"));
    }
}
