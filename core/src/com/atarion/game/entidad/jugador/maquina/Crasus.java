package com.atarion.game.entidad.jugador.maquina;

import com.atarion.game.entidad.Entidad;
import com.atarion.game.entidad.jugador.Jugador;
import com.atarion.game.entidad.jugador.humano.Humano;
import com.atarion.game.entidad.objeto.Bomba;
import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import java.util.HashSet;
import java.util.Iterator;

public class Crasus extends Maquina {

    private boolean colision = false, colisionbomba = false;
    private float temporizador = 0f;
    private HashSet<Entidad> bombas = new HashSet<Entidad>();

    public Crasus(boolean batalla) {
        super(batalla);

        this.vidainicial *= 4;
        this.vida *= this.vidainicial;

        this.textura = new Texture(Gdx.files.internal("crasus.png"));
    }

    @Override
    public MensajeJSON enviarEstado() {
        MensajeJSON estado = super.enviarEstado();
        estado.escribirAtributo("colision", "" + this.colision);
        estado.escribirAtributo("colisionbomba", "" + this.colisionbomba);
        estado.escribirAtributo("temporizador", "" + this.temporizador);
        //estado.escribirArray("bombas",this.bombas,ParteMensaje.FINAL);

        return estado;
    }

    @Override
    public void recibirEstado(String estado) {
        super.recibirEstado(estado);
    }

    @Override
    public void actualizarEstado() {
        super.actualizarEstado();

        if (!bombas.isEmpty()) {
            Iterator<Entidad> i = bombas.iterator();
            while (i.hasNext()) {
                i.next().actualizarEstado();
            }
        }
    }

    @Override
    public void jugar(Camera camara) {
        super.jugar(camara);

        if (!bombas.isEmpty()) {
            temporizador += Gdx.graphics.getDeltaTime();
            if (temporizador >= 1.0f) {
                Iterator<Entidad> i = bombas.iterator();
                while (i.hasNext()) {
                    ((Bomba) i.next()).cuentaAtras();
                }

                temporizador = 0f;
            }

            Iterator<Entidad> j = bombas.iterator();
            while (j.hasNext()) {
                Bomba bomba = (Bomba) j.next();

                this.enemigos.stream()
                        .forEach(enemigo -> {
                            if (bomba.overlaps(enemigo) && bomba.getDureza() > 0) {
                                if (!this.colisionbomba) {
                                    enemigo.setVida(enemigo.getVida() - bomba.getDureza());
                                    Gdx.app.log("INFO", "La bomba te hace 50 puntos de daño. Te quedan " + enemigo.getVida() + " vidas.");
                                    this.colisionbomba = true;
                                }
                            } else {
                                this.colisionbomba = false;
                            }
                        });
            }
        }
    }

    @Override
    public void agregarEnemigo(Jugador jugador) {
        this.enemigos.add(jugador);
        this.huir();
    }

    private void huir() {
        if (x < 0 + 100 + 100) {
            decision = 1;
        } else if (x > 1000 - 100 - 100) {
            decision = 2;
        } else if (y < 0 + 100 + 100) {
            decision = 3;
        } else if (y > 800 - 100 - 100) {
            decision = 4;
        } else {
            this.enemigos.stream().forEach(enemigo -> {
                if (enemigo.getX() > this.x && enemigo.getY() > this.y) {
                    decision = 8;
                } else if (enemigo.getX() > this.x && enemigo.getY() > this.y) {
                    decision = 6;
                } else if (enemigo.getX() > this.x && enemigo.getY() < this.y) {
                    decision = 7;
                } else if (enemigo.getX() < this.x && enemigo.getY() < this.y) {
                    decision = 5;
                } else if (enemigo.getX() < this.x) {
                    decision = 1;
                } else if (enemigo.getX() > this.x) {
                    decision = 2;
                } else if (enemigo.getY() < this.y) {
                    decision = 3;
                } else if (enemigo.getY() > this.y) {
                    decision = 4;
                }
            });
        }
    }

    private void comprobarColision() {
        if (!colision) {
            this.vida -= 10;
            Gdx.app.log("INFO: ", "a la bola le quedan " + this.vida + " vidas.");
            this.colision = true;
        }
    }

    @Override
    public void controlBordes() {
        if (this.y < 0) {
            this.y = 800 - 150;
            comprobarColision();
            decision = 5;
        } else if (this.y > 800 - 100) {
            this.y = 0 + 150;
            comprobarColision();
            decision = 7;
        } else if (this.x < 0) {
            this.x = 1000 - 150;
            comprobarColision();
            decision = 8;
        } else if (this.x > 1000 - 100) {
            this.x = 0 + 150;
            comprobarColision();
            decision = 6;
        } else {
            this.colision = false;
        }

        this.huir();
    }

    @Override
    public void activarEspecial() {
        this.textura = new Texture(Gdx.files.internal("control.png"));

        //A veces te amaga y no te invierte los controles 
        int probabilidad = (int) (Math.random() * 4 + 1);
        if (probabilidad > 1) {
            this.enemigos.stream().forEach(enemigo -> {
                ((Humano) enemigo).setInvertido(true);
            });
        }

        int posiciones = (int) (Math.random() * 2 + 1);
        switch (posiciones) {
            case 1:
                bombas.add(new Bomba(genesis, 0 + 200, 800 - 200 + 50));
                bombas.add(new Bomba(genesis, 0 + 200, 600 - 200));
                bombas.add(new Bomba(genesis, 0 + 200, 0 + 200 - 50));

                bombas.add(new Bomba(genesis, 1000 - 200, 800 - 200 + 50));
                bombas.add(new Bomba(genesis, 1000 - 200, 600 - 200));
                bombas.add(new Bomba(genesis, 1000 - 200, 0 + 200 - 50));
                break;
            case 2:
                bombas.add(new Bomba(genesis, 0 + 200, 800 - 200 + 50));
                bombas.add(new Bomba(genesis, 700 - 200, 800 - 200 + 50));
                bombas.add(new Bomba(genesis, 1000 - 200, 800 - 200 + 50));

                bombas.add(new Bomba(genesis, 0 + 200, 0 + 200 - 50));
                bombas.add(new Bomba(genesis, 700 - 200, 0 + 200 - 50));
                bombas.add(new Bomba(genesis, 1000 - 200, 0 + 200 - 50));
                break;
        }
    }

    @Override
    public void desactivarEspecial() {
        this.textura = new Texture(Gdx.files.internal("crasus.png"));

        this.enemigos.stream().forEach(enemigo -> {
            ((Humano) enemigo).setInvertido(false);
        });

        bombas.clear();
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
