package com.atarion.game.interfaz;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Interfaz implements Screen
{
    protected OrthographicCamera camara = null;
    protected SpriteBatch genesis = null;
    protected Music tema = null;
    protected Interfaz control = null;

    @Override
    public void show() 
    {
        if(control == null || control == this)
        {
            Gdx.app.setLogLevel(Application.LOG_INFO);
            
            camara = new OrthographicCamera();
            camara.setToOrtho(false, 1000, 800);
            
            genesis = new SpriteBatch();
            
            if(tema != null)
            {
                tema.play();
            }
            
            control = this;
        }
    }
    @Override
    public void hide() 
    {
        if(control.equals(this))
        {
            if(tema != null)
            {
                tema.stop();
            } 
            
            control = null;
        }
    }

    @Override
    public void render(float delta) 
    {
        // clear the screen with a dark blue color. The
        // arguments to glClearColor are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
      
        // tell the camera to update its matrices.
        camara.update();
        
        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        genesis.setProjectionMatrix(camara.combined);
    }
    @Override
    public void resize(int width, int height) 
    {
        
    }

    @Override
    public void pause() 
    {
        
    }
    @Override
    public void resume() 
    {
        
    }

    @Override
    public void dispose() 
    {
       genesis.dispose();
       
       if(tema != null)
       {
           tema.dispose();
       }
    }
}
