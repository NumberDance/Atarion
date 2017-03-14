package com.atarion.game.entidad.jugador.humano;

import com.atarion.game.entidad.jugador.Jugador;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Templar extends Humano
{
    public Templar(Batch genesis) 
    {
        super(genesis);
        this.textura = new Texture(Gdx.files.internal("templar.png"));
    }
    
    @Override
    public void agregarEnemigo(Jugador jugador) 
    {
        enemigo = jugador;
    }
    
    @Override
    public void colisionJugador(Jugador jugador)
    {
        switch(jugador.getDireccion())
        {
            case PARADO:
                switch (this.direccion) 
                {
                    case ARRIBA:
                        jugador.setY(jugador.getY() + 100);
                    break;
                    case ABAJO:
                        jugador.setY(jugador.getY() - 100);
                    break;
                    case DERECHA:
                        jugador.setX(jugador.getX() + 100);
                    break;
                    case IZQUIERDA:
                        jugador.setX(jugador.getX() - 100);
                    break;
                }
            break;
            case ARRIBA:
                jugador.setY(jugador.getY() - 100);
            break;
            case ABAJO:
                jugador.setY(jugador.getY() + 100);
            break;
            case DERECHA:
                jugador.setX(jugador.getX() - 100);
            break;
            case IZQUIERDA:
                jugador.setX(jugador.getX() + 100);
            break;
            case LATERAL_DERECHO_ARRIBA:
                jugador.setX(jugador.getX() - 100);
                jugador.setY(jugador.getY() - 100);
            break;
            case LATERAL_DERECHO_ABAJO:
                jugador.setX(jugador.getX() - 100);
                jugador.setY(jugador.getY() + 100);
            break;
            case LATERAL_IZQUIERDO_ARRIBA:
                jugador.setX(jugador.getX() + 100);
                jugador.setY(jugador.getY() - 100);
            break;
            case LATERAL_IZQUIERDO_ABAJO:
                jugador.setX(jugador.getX() + 100);
                jugador.setY(jugador.getY() + 100);
            break;
        }
        
        Gdx.app.log
        (
                "INFO",
                "Las barreras de clase Templar no reciben daño de contacto."
        );
    }
    
    @Override
    public void activarEspecial() 
    {
        velocidad *= 6;
        fuerza *= 6;
    }
    @Override
    public void desactivarEspecial() 
    {
        velocidad /= 6;
        fuerza /= 6;
    }
}
