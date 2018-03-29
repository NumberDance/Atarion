package com.atarion.game.entidad.jugador.humano.cannon;


import com.atarion.game.entidad.jugador.Jugador;
import com.atarion.game.entidad.jugador.humano.Humano;
import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.badlogic.gdx.Gdx;
import org.json.JSONObject;
import org.json.JSONTokener;


public abstract class Cannon extends Humano
{
    protected int rebote = 200;

   
    public Cannon(boolean tu)
    {
        super(tu);
        
        this.fuerza *= 2;
        this.vida /= 2;
    }


    @Override
    public MensajeJSON enviarEstado()
    { return super.enviarEstado().escribirAtributo("rebote","" + this.rebote); }
    @Override
    public void recibirEstado(String estado)
    { 
        super.recibirEstado(estado); 
        
        JSONObject objeto = new JSONObject(new JSONTokener(estado));
        this.rebote = objeto.getInt("rebote");
    }

    
    @Override
    public void colisionJugador(Jugador jugador)
    {
        switch(jugador.getDireccion())
        {
            case PARADO:
                switch(this.direccion) 
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
            "Las barreras de clase Canon no reciben daño de contacto."
        );
    }
    
    
    @Override
    public abstract void activarEspecial();
    @Override
    public abstract void desactivarEspecial();
}
