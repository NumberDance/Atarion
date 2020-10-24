package com.atarion.game.entidad.jugador.humano;

import com.atarion.game.entidad.habilidad.Habilidad;
import com.atarion.game.entidad.jugador.Direccion;
import com.atarion.game.entidad.objeto.Objeto;
import com.atarion.game.entidad.jugador.Jugador;
import com.atarion.game.entidad.objeto.recuperables.memoria.Secuencia;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Humano extends Jugador {

    protected boolean tu;
    protected Habilidad habilidad = null;
    protected ClaseHumano clase = null;
    protected Secuencia secuencia;
    protected boolean gusto, oido, olfato, vista, tacto;

    public Humano(boolean tu, boolean batalla) {
        super(batalla);

        this.tu = tu;
        this.y = 30;
    }

    @Override
    public void actualizarEstado() {
        super.actualizarEstado();

        if (habilidad != null) {
            habilidad.actualizarEstado();
        }
    }

    @Override
    public void agregarEnemigo(Jugador jugador) {
        enemigo = jugador;
    }

    @Override
    public void jugar(Camera camara) {
        if (tu) {
            super.jugar(camara);
            
            this.controlEspecial();
            this.controlMovimientoTu(camara);
            this.controlBordesTu();
        }
    }

    @Override
    protected final void controlMovimiento() {
    }

    @Override
    protected void controlEspecial() {
        cronometro += Gdx.graphics.getDeltaTime();
        if (cronometro >= 1.0f) {
            if (activado) {
                if (activo == 0) {
                    this.desactivarEspecial();
                    this.activo = this.tiempoactivo;
                    Gdx.app.log("INFO", "El modulo se agoto.");
                    activado = false;
                } else if (parado) {
                    this.desactivarEspecial();

                    this.parado = false;
                    activado = false;
                } else {
                    activo--;
                    Gdx.app.log("INFO", "El modulo se agotara en " + tiempoactivo + " segundos.");
                }
            }

            if (recarga > 0) {
                recarga--;
                Gdx.app.log("INFO", "El modulo estara disponible en " + recarga + " segundos.");
            } else {
                Gdx.app.log("INFO", "El modulo ya esta disponible.");
            }

            cronometro = 0.0f;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && !controlado) {
            if (recarga == 0) {
                activarEspecial();

                recarga = tiemporecarga;
                activado = true;
            }
        }
    }

    @Override
    protected void controlBordes() {
    }

    @Override
    public void colisionObjeto(Objeto objeto) {
    }

    @Override
    public void colisionJugador(Jugador jugador) {
    }

    private void controlMovimientoTu(Camera camara) {
        camara.position.x = this.x;
        camara.position.y = this.y;

        float movimiento = 200 * Gdx.graphics.getDeltaTime() * this.velocidad;

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (invertido) {
                this.y -= movimiento;
                camara.position.y -= movimiento;

                direccion = Direccion.ABAJO;
            } else {
                this.y += movimiento;
                camara.position.y += movimiento;

                direccion = Direccion.ARRIBA;

                if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                    this.x += movimiento;
                    camara.position.x += movimiento;

                    direccion = Direccion.LATERAL_DERECHO_ARRIBA;
                } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                    this.x -= movimiento;
                    camara.position.x -= movimiento;

                    direccion = Direccion.LATERAL_IZQUIERDO_ARRIBA;
                }
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (invertido) {
                this.y += movimiento;
                camara.position.y += movimiento;

                direccion = Direccion.ARRIBA;
            } else {
                this.y -= movimiento;
                camara.position.y -= movimiento;

                direccion = Direccion.ABAJO;

                if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                    this.x += movimiento;
                    camara.position.x += movimiento;

                    direccion = Direccion.LATERAL_DERECHO_ABAJO;
                } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                    this.x -= movimiento;
                    camara.position.x -= movimiento;

                    direccion = Direccion.LATERAL_IZQUIERDO_ABAJO;
                }
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (invertido) {
                this.x -= movimiento;
                camara.position.x -= movimiento;

                direccion = Direccion.IZQUIERDA;
            } else {
                this.x += movimiento;
                camara.position.x += movimiento;

                direccion = Direccion.DERECHA;

                if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                    this.y += movimiento;
                    camara.position.y += movimiento;

                    direccion = Direccion.LATERAL_DERECHO_ARRIBA;
                } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                    this.y -= movimiento;
                    camara.position.y -= movimiento;

                    direccion = Direccion.LATERAL_DERECHO_ABAJO;
                }
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (invertido) {
                this.x += movimiento;
                camara.position.x += movimiento;

                direccion = Direccion.DERECHA;
            } else {
                this.x -= movimiento;
                camara.position.x -= movimiento;

                direccion = Direccion.IZQUIERDA;

                if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                    this.y += movimiento;
                    camara.position.y += movimiento;

                    direccion = Direccion.LATERAL_IZQUIERDO_ARRIBA;
                } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                    this.y -= movimiento;
                    camara.position.y -= movimiento;

                    direccion = Direccion.LATERAL_IZQUIERDO_ABAJO;
                }
            }
        } else {
            direccion = Direccion.PARADO;
        }
    }

    private void controlBordesTu() {
        if (this.y < 0) {
            this.y = 0;
        }

        if (this.y > Gdx.graphics.getHeight() - 25) {
            this.y = Gdx.graphics.getHeight() - 25;
        }

        if (this.x < 0) {
            this.x = 0;
        }

        if (this.x > Gdx.graphics.getWidth() - 85) {
            this.x = Gdx.graphics.getWidth() - 85;
        }
    }

    private void conversarTu(Jugador jugador) {
        this.texto = new BitmapFont();
        this.texto.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        this.texto.getData().setScale(2f);

        this.texto.draw(genesis, jugador.getConversacion(), x, y);
    }
}
