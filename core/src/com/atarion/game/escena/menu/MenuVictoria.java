package com.atarion.game.escena.menu;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class MenuVictoria extends Menu
{
    private BitmapFont titulo,descripcion,texto;
    
    @Override
    public void render(float delta)
    {
        super.render(delta);
        
        titulo = new BitmapFont();
        descripcion = new BitmapFont();
        texto = new BitmapFont();
        
        genesis.begin();
        
        titulo.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        titulo.getData().setScale(5f);
        titulo.draw(genesis, "FELICHITAT", 350, 750);
        
        descripcion.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        descripcion.getData().setScale(2f);
        descripcion.draw(genesis, "Dat ball ate da poo poo. Like icecream.", 330, 650);
        
        texto.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        texto.getData().setScale(2f);
        texto.draw(genesis, "A: Batalla de fuerza. \nB: Batalla de maña.\nC: Batalla de resistencia.", 370, 550);
        
        genesis.end();
    }
    
    public void dispose()
    {
        super.dispose();
        
        titulo.dispose();
        descripcion.dispose();
        texto.dispose();
    }
}
