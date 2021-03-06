package com.atarion.game.entidad.jugador.humano.trench;

import com.atarion.game.entidad.jugador.humano.Humano;
import com.atarion.game.entidad.objeto.Objeto;
import com.atarion.game.entidad.objeto.ProyectilTrench;
import com.atarion.game.interfaz.escena.Escena;
import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import org.json.JSONObject;
import org.json.JSONTokener;

public abstract class Trench extends Humano {

    protected ProyectilTrench proyectil = null;
    protected boolean colisionproyectil = false;

    public Trench(boolean tu, boolean batalla) {
        super(tu, batalla);

        this.vida *= 2;
        this.velocidad /= 2;
        this.inmune = true;
    }

    @Override
    public MensajeJSON enviarEstado() {
        MensajeJSON estado = super.enviarEstado();
        estado.escribirAtributo("colisionproyectil", "" + this.colisionproyectil);

        if (this.proyectil != null) {
            estado.escribirAtributo("proyectil", this.proyectil.enviarEstado().getMensaje());
        } else {
            estado.escribirAtributo("proyectil", "null");
        }

        return estado;
    }

    @Override
    public void recibirEstado(String estado) {
        super.recibirEstado(estado);

        JSONObject objeto = new JSONObject(new JSONTokener(estado));
        if (objeto.getString("proyectil").equals("null")) {
            this.proyectil = null;
        } else {
            if (this.proyectil != null) {
                this.proyectil = new ProyectilTrench(genesis, objeto.getString("proyectil"));
            }
        }
        this.colisionproyectil = objeto.getBoolean("colisionproyectil");
    }

    @Override
    public void actualizarEstado() {
        super.actualizarEstado();

        if (proyectil != null) {
            proyectil.actualizarEstado();
        }
    }

    @Override
    public void jugar(Camera camara, Escena escena) {
        super.jugar(camara, escena);

        if (proyectil != null) {
            proyectil.lanzar();

            this.enemigos.stream()
                    .forEach(enemigo -> {
                        if (proyectil.overlaps(enemigo)) {
                            if (!this.colisionproyectil) {
                                enemigo.setVida(enemigo.getVida() - proyectil.getDureza());
                                Gdx.app.log("INFO", "El enemigo ha recibido 5 del proyectil. Le quedan " + enemigo.getVida() + " vidas.");
                                this.colisionproyectil = true;
                            }
                        } else if (proyectil.overlaps(this)) {
                            if (!this.colisionproyectil) {
                                enemigo.setVida(enemigo.getVida() - proyectil.getDureza());
                                Gdx.app.log("INFO", "Te has dado con tu proyectil. Te quedan " + enemigo.getVida() + " vidas.");
                                this.colisionproyectil = true;
                            }
                        } else {
                            this.colisionproyectil = false;
                        }

                        if (proyectil.isDestino()) {
                            proyectil = null;
                        }
                    });
        }
    }

    @Override
    public void controlEspecial() {
        super.controlEspecial();

        if (Gdx.input.isKeyPressed(Input.Keys.ALT_RIGHT) && !controlado) {
            boolean disparar = true;

            float origenx = this.x + 15, origeny = this.y - 15;
            float destinox = 0, destinoy = 0;

            switch (this.direccion) {
                case ARRIBA:
                    origeny += 70;

                    destinox = this.x + 15;
                    destinoy = 800;
                    break;
                case ABAJO:
                    origeny -= 70;

                    destinox = this.x + 15;
                    destinoy = 0;
                    break;
                case DERECHA:
                    origenx += 100;

                    destinox = 1000;
                    destinoy = this.y - 15;
                    break;
                case IZQUIERDA:
                    origenx -= 100;

                    destinox = 0;
                    destinoy = this.y - 15;
                    break;
                default:
                    disparar = false;
                    break;
            }

            if (disparar) {
                proyectil = new ProyectilTrench(genesis, origenx, origeny, destinox, destinoy);
            }
        }
    }

    @Override
    public void colisionObjeto(Objeto objeto) {
        if (this.inmune) {
            Gdx.app.log("INFO", "Las barreras de la clase Trench pueden atravesar objetos.");
        } else {
            Gdx.app.log("INFO", "De repente, te hiciste vulnerable a los objetos. 100 puntos de daño y te quedan " + this.getVida() + "vidas");
            this.vida -= objeto.getDureza();
        }
    }

    @Override
    public abstract void activarEspecial();

    @Override
    public abstract void desactivarEspecial();
}
