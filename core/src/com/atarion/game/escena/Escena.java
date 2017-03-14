package com.atarion.game.escena;

import com.atarion.game.entidad.jugador.humano.Humano;
import com.atarion.game.entidad.jugador.maquina.Maquina;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Escena implements Screen
{
    protected SpriteBatch genesis;
    private OrthographicCamera camara;
    protected Humano humano;
    protected Maquina maquina;
    private boolean colision = false;
    private int estado = -1; //-1 en curso, 0 ganado, 1 perdido
    
    @Override
    public void show() 
    {
        Gdx.app.setLogLevel(Application.LOG_INFO);
        
        // create the camera and the SpriteBatch
        camara = new OrthographicCamera();
        camara.setToOrtho(false, 1000, 800);
        genesis = new SpriteBatch();
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
      
        genesis.begin();
        humano.actualizarEstado();
        maquina.actualizarEstado();
        genesis.end();
      
        humano.jugar(camara);
        maquina.jugar(camara);
      
        if(maquina.overlaps(humano))
        {
            if(!colision)
            {
                humano.colisionJugador(maquina);
                maquina.colisionJugador(humano);
                colision = true;
            }
        } 
        else
        {
            colision = false;
        }
      
        if(humano.getVida() <= 0)
        {
            estado = 1;
        }
        else if(maquina.getVida() <= 0)
        {
            estado = 0;
        }
    }
    
    @Override
    public void dispose() 
    {
        // dispose of all the native resources
        genesis.dispose();
        humano.dispose();
        maquina.dispose();
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
    public void hide() 
    {
    }

    public int getEstado() 
    {
        return estado;
    } 
}
