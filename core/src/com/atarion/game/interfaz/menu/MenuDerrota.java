package com.atarion.game.interfaz.menu;

import com.atarion.game.Atarion;
import com.atarion.game.interfaz.escena.EscenaBrutus;
import com.atarion.game.interfaz.escena.EscenaClaudius;
import com.atarion.game.interfaz.escena.EscenaCrasus;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class MenuDerrota extends Menu
{
    private BitmapFont titulo,texto,volver;

    
    public MenuDerrota() 
    {
        super();    
        super.musica("grabbed.mp3");
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
        if(Gdx.input.isKeyPressed(Input.Keys.A)) 
        {
            Texture analisis = new Texture(Gdx.files.internal("crasusanalysis.png"));
            Atarion.getInstance().setScreen(new MenuSeleccion(new EscenaCrasus(),analisis)); 
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.B))
        {
            Texture analisis = new Texture(Gdx.files.internal("brutusanalysis.png"));
            Atarion.getInstance().setScreen(new MenuSeleccion(new EscenaBrutus(),analisis)); 
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.C))
        {
            Texture analisis = new Texture(Gdx.files.internal("claudiusanalysis.png"));
            Atarion.getInstance().setScreen(new MenuSeleccion(new EscenaClaudius(),analisis)); 
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
        { new MenuPrincipal().mostrar(); }
    }
}
