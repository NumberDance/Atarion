package com.atarion.game.interfaz.menu;

import com.atarion.game.Atarion;
import com.atarion.game.interfaz.menu.analisis.AnalisisTemplar;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class MenuTeclas extends Menu
{
    private BitmapFont controles;
    private BitmapFont volver;
    
    
    public MenuTeclas()
    {
        super();
        
        tema = Gdx.audio.newMusic(Gdx.files.internal("pirates.mp3"));
        tema.setLooping(true);
    }
    
    
    @Override
    public void render(float delta)
    {
        super.render(delta);
        
        controles = new BitmapFont();
        volver = new BitmapFont();
        
        genesis.begin();
        
        controles.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        controles.getData().setScale(2f);
        controles.draw(genesis, "Flechas para mover la barrera. \n Espacio para la habilidad especial.", 270, 550);
        
        volver.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        volver.getData().setScale(1f);
        volver.draw(genesis, "Flechas derecha e izquierda para recorrer éste tutorial.", 370, 350);
        
        genesis.end();
    }
    
    
    @Override
    public void dispose()
    {
        super.dispose();    
        controles.dispose();
    }

    
    @Override
    protected void controlarTeclado() 
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT))
        { Atarion.getInstance().setScreen(new AnalisisTemplar()); }
    }
}
