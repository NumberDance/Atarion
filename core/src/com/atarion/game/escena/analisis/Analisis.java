package com.atarion.game.escena.analisis;

import com.atarion.game.escena.menu.Menu;
import com.badlogic.gdx.graphics.Texture;

public class Analisis extends Menu 
{
    protected Texture analisis;
    
    @Override
    public void render(float delta)
    {
        super.render(delta);
        
        genesis.begin();
        genesis.draw(analisis,(1000 - 800) / 2,(800 - 480) / 2);
        genesis.end();
    }
    
    @Override
    public void dispose()
    {
        super.dispose();
        analisis.dispose();
    }
}
