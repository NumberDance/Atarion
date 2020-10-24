package com.atarion.game.interfaz.escena;

import com.atarion.game.Atarion;
import com.atarion.game.entidad.jugador.humano.ClaseHumano;
import com.atarion.game.entidad.jugador.humano.DummyGeneric;
import com.atarion.game.entidad.jugador.humano.Humano;
import com.atarion.game.entidad.jugador.humano.cannon.Anarchist;
import com.atarion.game.entidad.jugador.humano.cannon.DummyCannon;
import com.atarion.game.entidad.jugador.humano.cannon.Fanatic;
import com.atarion.game.entidad.jugador.humano.cannon.Templar;
import com.atarion.game.entidad.jugador.humano.trench.Avenger;
import com.atarion.game.entidad.jugador.humano.trench.Benefactor;
import com.atarion.game.entidad.jugador.humano.trench.DummyTrench;
import com.atarion.game.entidad.jugador.humano.trench.Guardian;
import com.atarion.game.entidad.jugador.humano.wheel.DummyWheel;
import com.atarion.game.entidad.jugador.humano.wheel.Merchant;
import com.atarion.game.entidad.jugador.humano.wheel.Traveler;
import com.atarion.game.entidad.jugador.humano.wheel.Visionary;
import com.atarion.game.entidad.jugador.maquina.Maquina;
import com.atarion.game.interfaz.Interfaz;
import com.atarion.game.interfaz.menu.MenuDerrota;
import com.atarion.game.interfaz.menu.MenuVictoria;

public abstract class Escena extends Interfaz {

    protected Humano tu = null, humano = null;
    protected Maquina maquina = null;
    protected boolean cmaquinatu = false, 
            ctuhumano = false, 
            cmaquinahumano = false;
    protected boolean batalla;

    public void entrar(ClaseHumano clase) {
        this.tu = this.asignarClase(this.tu, clase, true);

        if (this.humano != null) {
            this.tu.agregarEnemigo(this.humano);
            this.humano.agregarEnemigo(this.tu);
        }
        if (this.maquina != null) {
            tu.agregarEnemigo(maquina);
            maquina.agregarEnemigo(tu);
        }

        Atarion.getInstance().setScreen(this);
    }

    protected Humano asignarClase(Humano jugador, ClaseHumano clase, boolean tu) {
        switch (clase) {
            case ANARCHIST:
                jugador = new Anarchist(tu, this.batalla);
                break;
            case FANATIC:
                jugador = new Fanatic(tu, this.batalla);
                break;
            case TEMPLAR:
                jugador = new Templar(tu, this.batalla);
                break;

            case AVENGER:
                jugador = new Avenger(tu, this.batalla);
                break;
            case BENEFACTOR:
                jugador = new Benefactor(tu, this.batalla);
                break;
            case GUARDIAN:
                jugador = new Guardian(tu, this.batalla);
                break;

            case MERCHANT:
                jugador = new Merchant(tu, this.batalla);
                break;
            case TRAVELER:
                jugador = new Traveler(tu, this.batalla);
                break;
            case VISIONARY:
                jugador = new Visionary(tu, this.batalla);
                break;

            case DUMMYGENERIC:
                jugador = new DummyGeneric();
                break;
            case DUMMYCANNON:
                jugador = new DummyCannon();
                break;
            case DUMMYWHEEL:
                jugador = new DummyWheel();
                break;
            case DUMMYTRENCH:
                jugador = new DummyTrench();
                break;
        }

        return jugador;
    }

    @Override
    public void show() {
        super.show();

        this.tu.setGenesis(genesis);

        if (this.humano != null) {
            this.humano.setGenesis(genesis);
        }
        if (this.maquina != null) {
            this.maquina.setGenesis(genesis);
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        genesis.begin();

        this.tu.actualizarEstado();
        if (this.humano != null) {
            this.humano.actualizarEstado();
        }
        if (maquina != null) {
            maquina.actualizarEstado();
        }

        genesis.end();

        this.tu.jugar(camara);
        if (this.humano != null) {
            this.humano.jugar(camara);
        }
        if (maquina != null) {
            maquina.jugar(camara);
        }

        this.controlColisiones();
        this.controlResultado();
    }

    private void controlColisiones() {
        if (maquina != null) {
            if (maquina.overlaps(this.tu)) {
                if (!cmaquinatu) {
                    this.tu.colisionJugador(this.maquina);
                    maquina.colisionJugador(this.tu);

                    cmaquinatu = true;
                }
            } else {
                cmaquinatu = false;
            }
        }

        if (this.humano != null) {
            if (humano.overlaps(tu)) {
                if (!this.ctuhumano) {
                    tu.colisionJugador(humano);
                    humano.colisionJugador(tu);

                    this.ctuhumano = true;
                }
            } else {
                this.ctuhumano = false;
            }
        }

        if (humano != null && maquina != null) {
            if (humano.overlaps(maquina)) {
                if (!this.cmaquinahumano) {
                    maquina.colisionJugador(humano);
                    humano.colisionJugador(maquina);

                    this.cmaquinahumano = true;
                }
            } else {
                this.cmaquinahumano = false;
            }
        }
    }

    private void controlResultado() {
        if (tu.getVida() <= 0) {
            new MenuDerrota().mostrar();
        } else if (maquina != null && maquina.getVida() <= 0) {
            new MenuVictoria().mostrar();
        }
    }

    @Override
    public void dispose() {
        tu.dispose();

        if (this.maquina != null) {
            this.maquina.dispose();
        }

        if (this.humano != null) {
            this.humano.dispose();
        }
    }
}
