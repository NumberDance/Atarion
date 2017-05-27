package com.atarion.game.entidad.jugador.humano.cannon;

import com.atarion.game.entidad.jugador.Direccion;
import com.atarion.game.entidad.jugador.Jugador;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Anarchist extends Cannon
{
    private Jugador controlado = null;
    
    
    public Anarchist(Batch genesis)
    { 
        super(genesis); 
        this.textura = new Texture(Gdx.files.internal("anarchist.png"));
    }

    
    @Override
    public void jugar(Camera camara) 
    {   
        if(controlado == null)
        { super.jugar(camara); }
        else
        { this.controlarJugador(); }
    }   
    private void controlarJugador()
    {
        this.controlEspecial();
        this.controlBordes();
        
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) 
        {
            enemigo.setY(enemigo.getY() + 200 * Gdx.graphics.getDeltaTime() * enemigo.getVelocidad());
            enemigo.setDireccion(Direccion.ARRIBA);
                
            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            {
                enemigo.setX(enemigo.getX() + 200 * Gdx.graphics.getDeltaTime() * enemigo.getVelocidad());
                enemigo.setDireccion(Direccion.LATERAL_DERECHO_ARRIBA);
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            {
                enemigo.setX(enemigo.getX() - 200 * Gdx.graphics.getDeltaTime() * enemigo.getVelocidad());
                enemigo.setDireccion(Direccion.LATERAL_IZQUIERDO_ARRIBA);
            }
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
        {
            enemigo.setY(enemigo.getY() - 200 * Gdx.graphics.getDeltaTime() * enemigo.getVelocidad());
            enemigo.setDireccion(Direccion.ABAJO);
                
            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            {
                enemigo.setX(enemigo.getX() + 200 * Gdx.graphics.getDeltaTime() * enemigo.getVelocidad());
                enemigo.setDireccion(Direccion.LATERAL_DERECHO_ABAJO);
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            {
                enemigo.setX(enemigo.getX() - 200 * Gdx.graphics.getDeltaTime() * enemigo.getVelocidad());
                enemigo.setDireccion(Direccion.LATERAL_IZQUIERDO_ABAJO);
            }
        }      
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
            enemigo.setX(enemigo.getX() + 200 * Gdx.graphics.getDeltaTime() * enemigo.getVelocidad());
            enemigo.setDireccion(Direccion.DERECHA);
            
            if(Gdx.input.isKeyPressed(Input.Keys.UP))
            {
                enemigo.setY(enemigo.getY() + 200 * Gdx.graphics.getDeltaTime() * enemigo.getVelocidad());
                enemigo.setDireccion(Direccion.LATERAL_DERECHO_ARRIBA);
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
            {
                enemigo.setY(enemigo.getY() - 200 * Gdx.graphics.getDeltaTime() * enemigo.getVelocidad());
                enemigo.setDireccion(Direccion.LATERAL_DERECHO_ABAJO);
            }
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) 
        {
            enemigo.setX(enemigo.getX() - 200 * Gdx.graphics.getDeltaTime() * enemigo.getVelocidad());
            enemigo.setDireccion(Direccion.IZQUIERDA);
            
            if(Gdx.input.isKeyPressed(Input.Keys.UP))
            {
                enemigo.setY(enemigo.getY() + 200 * Gdx.graphics.getDeltaTime() * enemigo.getVelocidad());
                enemigo.setDireccion(Direccion.LATERAL_IZQUIERDO_ARRIBA);
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
            {
                enemigo.setY(enemigo.getY() - 200 * Gdx.graphics.getDeltaTime() * enemigo.getVelocidad());
                enemigo.setDireccion(Direccion.LATERAL_IZQUIERDO_ABAJO);
            }
        }
        else
        { enemigo.setDireccion(Direccion.PARADO); }
        
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
        {
            if(enemigo.getRecarga() == 0)
            {
                enemigo.activarEspecial();
                
                enemigo.setRecarga(enemigo.getTiemporecarga());
                enemigo.setActivado(true);
            }
        }
    }

    
    @Override
    public void activarEspecial()
    { 
        this.controlado = this.enemigo; 
        this.controlado.setControlado(true);
    }
    @Override
    public void desactivarEspecial()
    { 
        this.controlado.setControlado(false);
        this.controlado = null; 
    }
}