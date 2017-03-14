package com.atarion.game.escena.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Menu implements Screen 
{
    private OrthographicCamera camara;
    protected SpriteBatch genesis;
    
    public Menu()
    {
        camara = new OrthographicCamera();
        camara.setToOrtho(false, 1000, 800);
        
        genesis = new SpriteBatch();
    }
    
    @Override
    public void show() 
    {
    }

    @Override
    public void render(float delta) 
    {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camara.update();
        genesis.setProjectionMatrix(camara.combined);
    }

    @Override
    public void resize(int width, int height) {
        
    }

    @Override
    public void pause() {
        
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void hide() 
    {
        dispose();
    }

    @Override
    public void dispose() 
    {
        genesis.dispose();
    }
}

