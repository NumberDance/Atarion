package com.atarion.game.entidad.jugador.humano.cannon;

import com.atarion.game.entidad.jugador.Direccion;
import com.atarion.game.entidad.jugador.Jugador;
import com.atarion.game.entidad.jugador.humano.ClaseHumano;
import com.atarion.game.interfaz.escena.Escena;
import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Anarchist extends Cannon {
    
    private Jugador controlado = null;
    
    public Anarchist(boolean tu, boolean batalla) {
        super(tu, batalla);
        
        this.clase = ClaseHumano.ANARCHIST;
        this.textura = new Texture(Gdx.files.internal("textures/anarchist.png"));
    }
    
    @Override
    public MensajeJSON enviarEstado() {
        return super.enviarEstado().escribirAtributo("controlado", "" + this.controlado);
    }
    
    @Override
    public void recibirEstado(String estado) {
        super.recibirEstado(estado);
        
        JSONObject objeto = new JSONObject(new JSONTokener(estado));
        //TODO
    }
    
    @Override
    public void jugar(Camera camara, Escena escena) {
        if (controlado == null) {
            super.jugar(camara, escena);
        } else {
            this.controlarJugador(escena);
            this.enemigos.forEach(enemigo -> {
                enemigo.setInteraccion(true);
            });
        }
    }
    
    private void controlarJugador(Escena escena) {
        this.controlEspecial();
        this.controlBordes(escena);
        
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            this.enemigos.forEach(enemigo -> {
                enemigo.setY(enemigo.getY() + 200 * Gdx.graphics.getDeltaTime() * enemigo.getVelocidad());
                enemigo.setDireccion(Direccion.ARRIBA);
                
                if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                    enemigo.setX(enemigo.getX() + 200 * Gdx.graphics.getDeltaTime() * enemigo.getVelocidad());
                    enemigo.setDireccion(Direccion.LATERAL_DERECHO_ARRIBA);
                } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                    enemigo.setX(enemigo.getX() - 200 * Gdx.graphics.getDeltaTime() * enemigo.getVelocidad());
                    enemigo.setDireccion(Direccion.LATERAL_IZQUIERDO_ARRIBA);
                }
            });
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            this.enemigos.forEach(enemigo -> {
                enemigo.setY(enemigo.getY() - 200 * Gdx.graphics.getDeltaTime() * enemigo.getVelocidad());
                enemigo.setDireccion(Direccion.ABAJO);
                
                if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                    enemigo.setX(enemigo.getX() + 200 * Gdx.graphics.getDeltaTime() * enemigo.getVelocidad());
                    enemigo.setDireccion(Direccion.LATERAL_DERECHO_ABAJO);
                } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                    enemigo.setX(enemigo.getX() - 200 * Gdx.graphics.getDeltaTime() * enemigo.getVelocidad());
                    enemigo.setDireccion(Direccion.LATERAL_IZQUIERDO_ABAJO);
                }
            });
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.enemigos.forEach(enemigo -> {
                enemigo.setX(enemigo.getX() + 200 * Gdx.graphics.getDeltaTime() * enemigo.getVelocidad());
                enemigo.setDireccion(Direccion.DERECHA);
                
                if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                    enemigo.setY(enemigo.getY() + 200 * Gdx.graphics.getDeltaTime() * enemigo.getVelocidad());
                    enemigo.setDireccion(Direccion.LATERAL_DERECHO_ARRIBA);
                } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                    enemigo.setY(enemigo.getY() - 200 * Gdx.graphics.getDeltaTime() * enemigo.getVelocidad());
                    enemigo.setDireccion(Direccion.LATERAL_DERECHO_ABAJO);
                }
            });
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.enemigos.forEach(enemigo -> {
                enemigo.setX(enemigo.getX() - 200 * Gdx.graphics.getDeltaTime() * enemigo.getVelocidad());
                enemigo.setDireccion(Direccion.IZQUIERDA);
                
                if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                    enemigo.setY(enemigo.getY() + 200 * Gdx.graphics.getDeltaTime() * enemigo.getVelocidad());
                    enemigo.setDireccion(Direccion.LATERAL_IZQUIERDO_ARRIBA);
                } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                    enemigo.setY(enemigo.getY() - 200 * Gdx.graphics.getDeltaTime() * enemigo.getVelocidad());
                    enemigo.setDireccion(Direccion.LATERAL_IZQUIERDO_ABAJO);
                }
            });
        } else {
            this.enemigos.forEach(enemigo -> {
                enemigo.setDireccion(Direccion.PARADO);
            });
        }
        
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            this.enemigos.forEach(enemigo -> {
                if (enemigo.getRecarga() == 0) {
                    enemigo.activarEspecial();
                    
                    enemigo.setRecarga(enemigo.getTiemporecarga());
                    enemigo.setActivado(true);
                }
            });
        }
    }
    
    @Override
    public void activarEspecial() {
        this.controlado = this.enemigos.get(0);
        this.controlado.setControlado(true);
    }
    
    @Override
    public void desactivarEspecial() {
        this.controlado.setControlado(false);
        this.controlado = null;
    }
}
