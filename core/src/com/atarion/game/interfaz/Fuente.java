package com.atarion.game.interfaz;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;


public class Fuente 
{
    public static final String FONT_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";
    
    Fuente()
    {
        FileHandle fontFile = Gdx.files.internal("Pinewood.ttf");
        //FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);

    }
}
