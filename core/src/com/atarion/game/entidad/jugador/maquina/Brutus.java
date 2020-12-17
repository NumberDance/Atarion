package com.atarion.game.entidad.jugador.maquina;

import com.atarion.game.entidad.jugador.Jugador;
import com.atarion.game.entidad.objeto.ProyectilBrutus;
import com.atarion.game.interfaz.escena.Escena;
import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Brutus extends Maquina {

    private final List<ProyectilBrutus> proyectiles = new LinkedList<>();
    private boolean colisionproyectil = false;

    public Brutus(boolean batalla) {
        super(batalla);

        this.textura = new Texture(Gdx.files.internal("textures/brutus.png"));
        this.fuerza *= 4;
        this.velocidad /= 2;
    }

    @Override
    public MensajeJSON enviarEstado() {
        MensajeJSON estado = super.enviarEstado();
        estado.escribirAtributo("colisionproyectil", "" + this.colisionproyectil);

        this.proyectiles.stream().forEach(proyectil -> {
            if (proyectil != null) {
                estado.escribirAtributo("proyectil", proyectil.enviarEstado().getMensaje());
            } else {
                estado.escribirAtributo("proyectil", "null");
            }
        });

        return estado;
    }

    @Override
    public void recibirEstado(String estado) {
        super.recibirEstado(estado);

        JSONObject objeto = new JSONObject(new JSONTokener(estado));

        this.proyectiles.stream().forEach(proyectil -> {
            if (objeto.getString("proyectil").equals("null")) {
                proyectil = null;
            } else {
                if (proyectil != null) {
                    proyectiles.add(new ProyectilBrutus(genesis, objeto.getString("proyectil")));
                }
            }
        });

        this.colisionproyectil = objeto.getBoolean("colisionproyectil");
    }

    @Override
    public void actualizarEstado() {
        super.actualizarEstado();

        this.proyectiles.stream().forEach(proyectil -> {
            if (proyectil != null) {
                proyectil.actualizarEstado();
            }
        });
    }

    @Override
    public void agregarEnemigo(Jugador jugador) {
        this.enemigos.add(jugador);
        this.perseguir();
    }

    @Override
    public void jugar(Camera camara, Escena escena) {
        super.jugar(camara,escena);

        this.proyectiles.stream()
                .forEach(proyectil -> {
                    if (proyectil != null) {
                        proyectil.lanzar();

                        this.enemigos.stream().forEach(enemigo -> {
                            if (proyectil.overlaps(enemigo)) {
                                if (!this.colisionproyectil) {
                                    enemigo.setVida(enemigo.getVida() - proyectil.getDureza());
                                    Gdx.app.log("INFO", "Has recibido 10 del proyectil. Te quedan " + enemigo.getVida() + " vidas.");
                                    this.colisionproyectil = true;
                                }
                            } else if (proyectil.overlaps(this)) {
                                if (!this.colisionproyectil) {
                                    this.setVida(this.vida - proyectil.getDureza());
                                    Gdx.app.log("INFO", "La bola ha recibido 10 de su medicina. Le quedan " + vida + " vidas.");

                                    this.colisionproyectil = true;
                                    this.perseguir();
                                }
                            } else {
                                this.colisionproyectil = false;
                            }
                        });
                    }
                });
    }

    private void perseguir() {
        this.enemigos.stream().forEach(enemigo -> {
            float ccentrox = this.getX() - (enemigo.getWidth() / 2);
            float ccentroy = this.getY() - (enemigo.getHeight() / 2);

            float ecentrox = enemigo.getX() - (enemigo.getWidth() / 2);
            float ecentroy = enemigo.getY() - (enemigo.getHeight() / 2);

            if (enemigo.getX() > this.x && enemigo.getY() > this.y) {
                decision = 5;
            } else if (enemigo.getX() > this.x && enemigo.getY() < this.y) {
                decision = 6;
            } else if (enemigo.getX() < this.x && enemigo.getY() > this.y) {
                decision = 7;
            } else if (enemigo.getX() < this.x && enemigo.getY() < this.y) {
                decision = 8;
            } else if (enemigo.getX() > this.x) {
                decision = 1;
            } else if (enemigo.getX() < this.x) {
                decision = 2;
            } else if (enemigo.getY() > this.y) {
                decision = 3;
            } else if (enemigo.getY() < this.y) {
                decision = 4;
            }
        });
    }

    @Override
    public void controlBordes(Escena escena) {
        if (this.y < 0) {
            this.y = 0;
            this.perseguir();
        } else if (this.y > 800 - 105) {
            this.y = 800 - 110;
            this.perseguir();
        }

        if (this.x < 0) {
            this.x = 0;
            this.perseguir();
        } else if (this.x > 1000 - 105) {
            this.x = 1000 - 110;
            this.perseguir();
        }

        if (this.fase == 10) {
            this.perseguir();
        }
    }

    @Override
    public void activarEspecial() {
        this.textura = new Texture(Gdx.files.internal("textures/hunt.png"));

        this.enemigos.stream().forEach(enemigo -> {
            proyectiles.add(new ProyectilBrutus(genesis, 50, 50, enemigo.getX(), enemigo.getY()));
        });

        this.velocidad += 0.1;

        this.enemigos.stream().forEach(enemigo -> {
            enemigo.setVelocidad(enemigo.getVelocidad() - 0.1f);
        });
    }

    @Override
    public void desactivarEspecial() {
        this.textura = new Texture(Gdx.files.internal("textures/brutus.png"));
        this.proyectiles.clear();

        this.perseguir();
    }

    @Override
    protected void faseDos() {
    }

    @Override
    protected void faseTres() {
    }

    @Override
    protected void faseCuatro() {
    }

    @Override
    protected void faseCinco() {
    }

    @Override
    protected void faseSeis() {
    }

    @Override
    protected void faseSiete() {
    }

    @Override
    protected void faseOcho() {
    }

    @Override
    protected void faseNueve() {
    }

    @Override
    protected void faseDiez() {
    }
}
