package com.atarion.game.entidad.jugador.humano;

import com.atarion.game.entidad.habilidad.HabilidadTemplar;
import com.atarion.game.entidad.jugador.Jugador;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Templar extends Humano
{
    private HabilidadTemplar habilidad = null;
    protected int rebote = 200;
    
    
    public Templar(Batch genesis) 
    {
        super(genesis);
        this.textura = new Texture(Gdx.files.internal("templar.png"));
    }
    
    
    @Override
    public void actualizarEstado()
    {
       super.actualizarEstado();
        
       if(habilidad != null)
       { habilidad.actualizarEstado(); }
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
                        jugador.setY(jugador.getY() + this.rebote);
                    break;
                    case ABAJO:
                        jugador.setY(jugador.getY() - this.rebote);
                    break;
                    case DERECHA:
                        jugador.setX(jugador.getX() + this.rebote);
                    break;
                    case IZQUIERDA:
                        jugador.setX(jugador.getX() - this.rebote);
                    break;
                }
            break;
            case ARRIBA:
                jugador.setY(jugador.getY() - this.rebote);
            break;
            case ABAJO:
                jugador.setY(jugador.getY() + this.rebote);
            break;
            case DERECHA:
                jugador.setX(jugador.getX() - this.rebote);
            break;
            case IZQUIERDA:
                jugador.setX(jugador.getX() + this.rebote);
            break;
            case LATERAL_DERECHO_ARRIBA:
                jugador.setX(jugador.getX() - this.rebote);
                jugador.setY(jugador.getY() - this.rebote);
            break;
            case LATERAL_DERECHO_ABAJO:
                jugador.setX(jugador.getX() - this.rebote);
                jugador.setY(jugador.getY() + this.rebote);
            break;
            case LATERAL_IZQUIERDO_ARRIBA:
                jugador.setX(jugador.getX() + this.rebote);
                jugador.setY(jugador.getY() - this.rebote);
            break;
            case LATERAL_IZQUIERDO_ABAJO:
                jugador.setX(jugador.getX() + this.rebote);
                jugador.setY(jugador.getY() + this.rebote);
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
        velocidad *= 4;
        fuerza *= 4;
        rebote *= 4;
        
        this.habilidad = new HabilidadTemplar(genesis,this);
    }
    @Override
    public void desactivarEspecial() 
    {
        velocidad /= 4;
        fuerza /= 4;
        rebote /= 4;
        
        this.habilidad = null;
    }
}
