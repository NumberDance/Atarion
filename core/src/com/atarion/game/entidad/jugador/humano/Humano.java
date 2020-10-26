package com.atarion.game.entidad.jugador.humano;

import com.atarion.game.entidad.habilidad.Habilidad;
import com.atarion.game.entidad.jugador.Direccion;
import com.atarion.game.entidad.jugador.Jugador;
import com.atarion.game.entidad.objeto.Objeto;
import com.atarion.game.entidad.objeto.recuperables.memoria.Secuencia;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
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
        this.enemigos.add(jugador);
    }

    // Solo se hacen cosas con el jugador activo de cada cliente, el resto
    // actualiza estados
    @Override
    public void jugar(Camera camara) {
        if (tu) {
            super.jugar(camara);

            this.controlMovimientoTu(camara);
            this.controlEspecial();
            this.controlBordes();
        }
    }

    @Override
    protected void controlEspecial() {
        cronometro += Gdx.graphics.getDeltaTime();
        if (cronometro >= 1.0f) {
            if (activado) {
                if (activo == 0) {
                    this.desactivarEspecial();
                    this.activo = this.tiempoactivo;
                    //Ojo: causa problemas de memoria en partidas largas
                    //Gdx.app.log("INFO", "El modulo se agoto.");
                    activado = false;
                } else if (parado) {
                    this.desactivarEspecial();

                    this.parado = false;
                    activado = false;
                } else {
                    activo--;
                    //Ojo: causa problemas de memoria en partidas largas
                    //Gdx.app.log("INFO", "El modulo se agotara en " + tiempoactivo + " segundos.");
                }
            }

            if (recarga > 0) {
                recarga--;
                //Ojo: causa problemas de memoria en partidas largas
                //Gdx.app.log("INFO", "El modulo estara disponible en " + recarga + " segundos.");
            } else {
                //Ojo: causa problemas de memoria en partidas largas
                //Gdx.app.log("INFO", "El modulo ya esta disponible.");
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

    @Override
    protected void controlBordes() {
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

    /*private void conversar(Jugador jugador) {
        this.texto = new BitmapFont();
        this.texto.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        this.texto.getData().setScale(2f);

        this.texto.draw(genesis, jugador.getConversacion(), x, y);
    }*/

    // No se hace nada con el control de movimiento y colisiones si 
    // no se trata del jugador humano activo, el resto de jugadores del online
    // se encargan de actualizar sus estados a través del hilo cliente.
    @Override
    protected void controlMovimiento() {
    }

    @Override
    public void colisionJugador(Jugador jugador) {
    }

    @Override
    public void colisionObjeto(Objeto objeto) {
    }
}
