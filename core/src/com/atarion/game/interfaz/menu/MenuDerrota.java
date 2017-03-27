package com.atarion.game.interfaz.menu;

import com.atarion.game.Atarion;
import com.atarion.game.interfaz.menu.analisis.AnalisisBrutus;
import com.atarion.game.interfaz.menu.analisis.AnalisisClaudius;
import com.atarion.game.interfaz.menu.analisis.AnalisisCrasus;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class MenuDerrota extends Menu
{
    private BitmapFont titulo,texto,volver;

    public MenuDerrota() 
    {
        super();
        
        tema = Gdx.audio.newMusic(Gdx.files.internal("grabbed.mp3"));
    }
    
    @Override
    public void render(float delta)
    {
        super.render(delta);
        
        titulo = new BitmapFont();
        texto = new BitmapFont();
        volver = new BitmapFont();
        
        genesis.begin();
        
        titulo.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        titulo.getData().setScale(5f);
        titulo.draw(genesis, "DERROTA", 350, 750);
        
        texto.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        texto.getData().setScale(2f);
        texto.draw(genesis, "A: Batalla de fuerza. \nB: Batalla de maña.\nC: Batalla de resistencia.", 370, 550);
        
        volver.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        volver.getData().setScale(1.5f);
        volver.draw(genesis, "ESC para volver al menú principal.", 370, 350);
        
        genesis.end();
    }
    
    @Override
    public void dispose()
    {
        super.dispose();
        
        titulo.dispose();
        texto.dispose();
    }

    @Override
    protected void controlarTeclado() 
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.A)) 
        {
            Atarion.getInstance().setScreen(new AnalisisCrasus());
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.B))
        {
            Atarion.getInstance().setScreen(new AnalisisBrutus());
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.C))
        {
            Atarion.getInstance().setScreen(new AnalisisClaudius());
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
        {
            Atarion.getInstance().setScreen(new MenuPrincipal());
        }
    }
}
